package uk.gov.justice.json.generator.array;

import static javax.json.Json.createArrayBuilder;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MAX_ITEMS;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MIN_ITEMS;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.json.generator.JsonValueGenerator;

import java.util.concurrent.ThreadLocalRandom;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

import org.everit.json.schema.ArraySchema;

/**
 * Random generator for JSON arrays where all items adhere to the same schema.
 */
public class ListArrayGenerator extends JsonValueGenerator<JsonArray> {

    private JsonValueGenerator<?> itemGenerator;

    private int minItems;
    private int maxItems;

    /**
     * TODO: Uniqueness
     * Constructor.
     * @param schema the array schema to base the generator on
     */
    public ListArrayGenerator(final ArraySchema schema) {

        minItems = schema.getMinItems() != null ? schema.getMinItems() : DEFAULT_MIN_ITEMS;
        maxItems = schema.getMaxItems() != null ? schema.getMaxItems() : DEFAULT_MAX_ITEMS;

        itemGenerator = generatorFor(schema.getAllItemSchema());
    }

    @Override
    public JsonArray next() {
        int numItems = ThreadLocalRandom.current().nextInt(minItems, maxItems + 1);
        JsonArrayBuilder builder = createArrayBuilder();
        for (int i = 0; i < numItems; i++) {
            builder.add(itemGenerator.next());
        }
        return builder.build();
    }
}
