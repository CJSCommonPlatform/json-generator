package uk.gov.justice.json.generator;

import uk.gov.justice.json.JsonSchemaLoader;
import uk.gov.justice.services.test.utils.core.helper.TypeCheck;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.json.JsonValue;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.junit.Test;

public class JsonObjectGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;


    @Test
    public void shouldGenerateFromASimpleObjectSchema() throws IOException, URISyntaxException {

        validateJsonObjectGeneratorJsonValue("src/test/resources/schemas/simple-property-schema.json");
    }

    @Test
    public void shouldGenerateFromComplexEnumWithMixedTypes() {

        validateJsonObjectGeneratorJsonValue("src/test/resources/schemas/enum-property-schema.json");
    }

    @Test
    public void shouldGenerateFromComplexArrayWithMixedTypes() {

        validateJsonObjectGeneratorJsonValue("src/test/resources/schemas/array-property-schema.json");
    }

    @Test
    public void shouldGenerateFromComplexOneOfScheme() {

        validateJsonObjectGeneratorJsonValue("src/test/resources/schemas/oneOf-property-schema.json");
    }

    @Test
    public void shouldGenerateFromReferencedScheme() {

        validateJsonObjectGeneratorJsonValue("src/test/resources/schemas/ref.json");
    }

    private void validateJsonObjectGeneratorJsonValue(final String schemaPath) {
        final Schema schema = JsonSchemaLoader.loadSchema(schemaPath);

        final JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator((ObjectSchema) schema);

        TypeCheck.typeCheck(jsonObjectGenerator, jsonValue -> JsonObjectGeneratorTest
                .this.validateJsonObject(schema, jsonValue))
                .verify(TypeCheck.Times.times(NUMBER_OF_TIMES));
    }

    private Boolean validateJsonObject(Schema schema, JsonValue jsonValue) {
        try {
            schema.validate(new JSONObject(jsonValue.toString()));
        } catch (ValidationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}