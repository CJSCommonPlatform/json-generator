package uk.gov.justice.json.generator;

import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generator.array.ListArrayGenerator;
import uk.gov.justice.json.generator.array.TupleArrayGenerator;

import javax.json.JsonArray;

import org.everit.json.schema.ArraySchema;

/**
 * Random generator for JSON arrays.
 */
public class JsonArrayGenerator extends JsonValueGenerator<JsonArray> {

    public static final int DEFAULT_MIN_ITEMS = 0;
    public static final int DEFAULT_MAX_ITEMS = 10;

    private JsonValueGenerator<JsonArray> arrayGenerator;

    /**
     * Constructor.
     * @param schema the array schema to base the generator on
     */
    public JsonArrayGenerator(final ArraySchema schema) {

        if (schema.getAllItemSchema() != null && schema.getItemSchemas() == null) {
            arrayGenerator = new ListArrayGenerator(schema);
        } else if (schema.getItemSchemas() != null) {
            arrayGenerator = new TupleArrayGenerator(schema);
        } else {
            throw new JsonGenerationException("Inconsistent array schema");
        }
    }

    @Override
    public JsonArray next() {
        JsonArray array = arrayGenerator.next();
        return array;
    }
}
