package uk.gov.justice.json.generator.array;

import static javax.json.Json.createArrayBuilder;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MAX_ITEMS;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MIN_ITEMS;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generator.JsonValueGenerator;
import uk.gov.justice.json.generator.value.UnspecifiedJsonValueGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.Schema;

/**
 * Random generator for JSON arrays.
 */
public class TupleArrayGenerator extends JsonValueGenerator<JsonArray> {

    private List<JsonValueGenerator<?>> itemGenerators;
    private JsonValueGenerator<?> additionalItemsGenerator;

    private int minItems;
    private int maxItems;

    /**
     * TODO: Uniqueness
     * Constructor.
     * @param schema the array schema to base the generator on
     */
    public TupleArrayGenerator(final ArraySchema schema) {

        minItems = schema.getMinItems() != null ? schema.getMinItems() : DEFAULT_MIN_ITEMS;
        maxItems = schema.getMaxItems() != null ? schema.getMaxItems() : DEFAULT_MAX_ITEMS;

        itemGenerators = new ArrayList<>();
        for (Schema itemSchema : schema.getItemSchemas()) {
            itemGenerators.add(generatorFor(itemSchema));
        }

        if (schema.permitsAdditionalItems()) {
            if (schema.getSchemaOfAdditionalItems() != null) {
                additionalItemsGenerator = generatorFor(schema.getSchemaOfAdditionalItems());
            } else {
                additionalItemsGenerator = new UnspecifiedJsonValueGenerator();
            }
        } else if (schema.getItemSchemas().size() < minItems) {
            throw new JsonGenerationException("Not enough item schemas for min items, and additional items are not allowed!");
        }
    }

    @Override
    public JsonArray next() {
        int numItems = ThreadLocalRandom.current().nextInt(minItems, maxItems + 1);
        JsonArrayBuilder builder = createArrayBuilder();
        for(int i = 0; i < numItems; i++) {
            if (i < itemGenerators.size()) {
                builder.add(itemGenerators.get(i).next());
            } else {
                builder.add(additionalItemsGenerator.next());
            }
        }
        return builder.build();
    }
}
