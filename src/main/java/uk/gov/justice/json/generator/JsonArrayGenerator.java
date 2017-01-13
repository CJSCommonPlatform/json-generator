package uk.gov.justice.json.generator;

import static javax.json.Json.createArrayBuilder;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import java.util.concurrent.ThreadLocalRandom;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import org.everit.json.schema.ArraySchema;

/**
 * Random generator for JSON arrays.
 */
public class JsonArrayGenerator extends JsonValueGenerator<JsonArray> {

    public static final int DEFAULT_MIN_ITEMS = 0;
    public static final int DEFAULT_MAX_ITEMS = 10;

    private JsonValueGenerator<?> itemGenerator;

    private int minItems;
    private int maxItems;

    /**
     * TODO: Array of item schemas
     * TODO: Uniqueness
     * TODO: AdditionalItems
     * Constructor.
     * @param schema the array schema to base the generator on
     */
    public JsonArrayGenerator(final ArraySchema schema) {

        minItems = schema.getMinItems() != null ? schema.getMinItems() : DEFAULT_MIN_ITEMS;
        maxItems = schema.getMaxItems() != null ? schema.getMaxItems() : DEFAULT_MAX_ITEMS;

        itemGenerator = generatorFor(schema.getAllItemSchema());
    }

    @Override
    public JsonArray next() {
        int numItems = ThreadLocalRandom.current().nextInt(minItems, maxItems + 1);
        JsonArrayBuilder builder = createArrayBuilder();
        for(int i = 0; i < numItems; i++) {
            builder.add(itemGenerator.next());
        }
        return builder.build();
    }
}
