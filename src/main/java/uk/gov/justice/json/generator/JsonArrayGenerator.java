package uk.gov.justice.json.generator;

import static javax.json.Json.createArrayBuilder;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.json.generator.value.UnspecifiedJsonValueGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.Schema;

/**
 * Random generator for JSON arrays.
 */
public class JsonArrayGenerator extends JsonValueGenerator<JsonArray> {

    public static final int DEFAULT_MIN_ITEMS = 0;
    public static final int DEFAULT_MAX_ITEMS = 10;

    private List<JsonValueGenerator<?>> itemGenerators;
    private JsonValueGenerator<?> itemGenerator;

    private int minItems;
    private int maxItems;

    /**
     * TODO: Unique values
     * Constructor.
     * @param schema the array schema to base the generator on
     */
    public JsonArrayGenerator(final ArraySchema schema) {

        minItems = schema.getMinItems() != null ? schema.getMinItems() : DEFAULT_MIN_ITEMS;
        maxItems = schema.getMaxItems() != null ? schema.getMaxItems() : DEFAULT_MAX_ITEMS;

        if (schema.getAllItemSchema() != null && schema.getItemSchemas() == null) {
            initialiseForListArrays(schema.getAllItemSchema());
        } else if (schema.getItemSchemas() != null && schema.getAllItemSchema() == null) {
            initialiseForTupleArrays(
                    schema.getItemSchemas(),
                    schema.permitsAdditionalItems(),
                    Optional.ofNullable(schema.getSchemaOfAdditionalItems()));
        } else if (schema.getItemSchemas() == null && schema.getAllItemSchema() == null) {
            initialiseForUnspecifiedArrays();
        } else {
            throw new JsonGenerationException("Inconsistent array schema");
        }
    }

    /**
     * Initialise this generator for generating simple arrays with a single item generator.
     * @param itemSchema the schema for all array items
     */
    private void initialiseForListArrays(final Schema itemSchema) {
        itemGenerator = generatorFor(itemSchema);
    }

    /**
     * Initialise this generator for generating tuple arrays with an optional schema for additional
     * items.
     * @param itemSchemas a list of schemas for the specified items
     * @param permitAdditionalItems true if the schema allows additional items
     * @param additionalItemSchema  an optional schema for additional items if the are allowed
     */
    private void initialiseForTupleArrays(final List<Schema> itemSchemas,
                                          final boolean permitAdditionalItems,
                                          final Optional<Schema> additionalItemSchema) {

        itemGenerators = new ArrayList<>();
        for (Schema itemSchema : itemSchemas) {
            itemGenerators.add(generatorFor(itemSchema));
        }

        if (permitAdditionalItems) {
            itemGenerator = additionalItemSchema
                    .map(JsonValueGenerators::generatorFor)
                    .orElseGet(UnspecifiedJsonValueGenerator::new);
        } else if (itemSchemas.size() < minItems) {
            throw new JsonGenerationException("Not enough item schemas for min items, and additional items are not allowed!");
        }
    }

    private void initialiseForUnspecifiedArrays() {
        itemGenerator = new UnspecifiedJsonValueGenerator();
    }

    @Override
    public JsonArray next() {
        final int numItems = ThreadLocalRandom.current().nextInt(minItems, maxItems + 1);
        JsonArrayBuilder builder = createArrayBuilder();
        for (int i = 0; i < numItems; i++) {
            builder.add(value(i));
        }
        return builder.build();
    }

    /**
     * Generate an array item for a given
     * index.
     * @param index the index of the array item to generate
     * @return a value that matches the schema for that index
     */
    private JsonValue value(final int index) {
        if (index >= maxItems) {
            throw new JsonGenerationException("Tried to generate a value for an array index greater than the max length");
        } else if (itemGenerators != null && index < itemGenerators.size()) {
            return itemGenerators.get(index).next();
        } else {
            return itemGenerator.next();
        }
    }
}
