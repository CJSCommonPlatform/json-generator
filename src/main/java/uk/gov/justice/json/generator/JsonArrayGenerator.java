package uk.gov.justice.json.generator;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static javax.json.Json.createArrayBuilder;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.EmptySchema;
import org.everit.json.schema.Schema;

/**
 * Random generator for JSON arrays.
 */
public class JsonArrayGenerator extends Generator<JsonArray> {

    public static final int DEFAULT_MIN_ITEMS = 0;
    public static final int DEFAULT_MAX_ITEMS = 10;

    private List<Generator<JsonValue>> indexItemGenerators;
    private Generator<JsonValue> otherItemsGenerator;

    private int minItems;
    private int maxItems;

    /**
     * TODO: Unique values
     * Constructor.
     */
    private JsonArrayGenerator(final int minItems,
                               final int maxItems,
                               final Generator<JsonValue> otherItemsGenerator,
                               final List<Generator<JsonValue>> indexItemGenerators) {
        this.indexItemGenerators = indexItemGenerators;
        this.otherItemsGenerator = otherItemsGenerator;
        this.minItems = minItems;
        this.maxItems = maxItems;
    }

    public static JsonArrayGenerator fromSchema(final ArraySchema schema) {

        final int minItems = schema.getMinItems() != null ? schema.getMinItems() : DEFAULT_MIN_ITEMS;
        final int maxItems = schema.getMaxItems() != null ? schema.getMaxItems() : DEFAULT_MAX_ITEMS;

        if (schema.getAllItemSchema() != null && schema.getItemSchemas() == null) {

            return listArrayGenerator(minItems, maxItems, schema.getAllItemSchema());

        } else if (schema.getAllItemSchema() == null && schema.getItemSchemas() != null) {

            return tupleArrayGenerator(
                    minItems,
                    maxItems,
                    schema.getItemSchemas(),
                    schema.permitsAdditionalItems(),
                    Optional.ofNullable(schema.getSchemaOfAdditionalItems()));

        } else if (schema.getItemSchemas() == null && schema.getAllItemSchema() == null) {

            return unspecifiedArrayGenerator(minItems, maxItems);

        } else {
            throw new JsonGenerationException("Inconsistent array schema");
        }
    }

    /**
     * Initialise this generator for generating simple arrays with a single item generator.
     * @param itemSchema the schema for all array items
     */
    private static JsonArrayGenerator listArrayGenerator(final int minItems, final int maxItems, final Schema itemSchema) {
        return new JsonArrayGenerator(minItems, maxItems, new JsonValueGenerator(itemSchema), emptyList());
    }

    /**
     * Initialise this generator for generating tuple arrays with an optional schema for additional
     * items.
     * @param itemSchemas a list of schemas for the specified items
     * @param permitAdditionalItems true if the schema allows additional items
     * @param additionalItemSchema  an optional schema for additional items if the are allowed
     */
    private static JsonArrayGenerator tupleArrayGenerator(final int minItems,
                                                          final int maxItems,
                                                          final List<Schema> itemSchemas,
                                                          final boolean permitAdditionalItems,
                                                          final Optional<Schema> additionalItemSchema) {

        if (!permitAdditionalItems && itemSchemas.size() < minItems) {
            throw new JsonGenerationException("Not enough item schemas for min items, and additional items are not allowed!");
        }

        final List<Generator<JsonValue>> indexItemGenerators = itemSchemas.stream()
                .map(JsonValueGenerator::new)
                .collect(toList());

        final Generator<JsonValue> otherItemsGenerator = permitAdditionalItems ? additionalItemSchema
                    .map(JsonValueGenerator::new)
                    .orElseGet(() -> new JsonValueGenerator(EmptySchema.INSTANCE)) : null;

        return new JsonArrayGenerator(minItems, maxItems, otherItemsGenerator, indexItemGenerators);
    }

    private static JsonArrayGenerator unspecifiedArrayGenerator(final int minItems, final int maxItems) {
        return new JsonArrayGenerator(minItems, maxItems, new JsonValueGenerator(EmptySchema.INSTANCE), emptyList());
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
     * Generate an array item for a given index.
     * @param index the index of the array item to generate
     * @return a value that matches the schema for that index
     */
    private JsonValue value(final int index) {
        if (index >= maxItems) {
            throw new JsonGenerationException("Tried to generate a value for an array index greater than the max length");
        } else if (index < indexItemGenerators.size()) {
            return indexItemGenerators.get(index).next();
        } else {
            return otherItemsGenerator.next();
        }
    }
}
