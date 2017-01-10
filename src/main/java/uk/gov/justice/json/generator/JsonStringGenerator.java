package uk.gov.justice.json.generator;

import javax.json.Json;
import javax.json.JsonString;

import org.everit.json.schema.StringSchema;

/**
 * Created by david on 10/01/17.
 */
public class JsonStringGenerator implements JsonValueGenerator {

    private StringGenerator stringGenerator;

    // TODO: Create string generator that implements all limitations specified by schema
    public JsonStringGenerator(final StringSchema schema) {
        if (schema.getPattern() != null) {
            stringGenerator = new RegexGenerator(schema.getPattern());
        }
    }

    @Override
    public JsonString nextValue() {
        return constructJsonString(stringGenerator.nextValue());
    }

    private JsonString constructJsonString(final String string) {
        return Json.createObjectBuilder().add("tmp", string).build().getJsonString("tmp");
    }
}
