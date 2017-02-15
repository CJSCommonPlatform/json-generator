package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.matcher.JsonValueSchemaMatcher.isValidForSchema;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.Times.times;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.typeCheck;

import uk.gov.justice.json.JsonSchemaLoader;
import uk.gov.justice.services.test.utils.core.helper.TypeCheck;

import java.io.IOException;
import java.net.URISyntaxException;

import org.everit.json.schema.ObjectSchema;
import org.junit.Test;

public class JsonObjectGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;

    @Test
    public void shouldGenerateFromASimpleObjectSchema() throws IOException, URISyntaxException {

        final ObjectSchema schema = (ObjectSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/simple-property-schema.json");
        final JsonObjectGenerator jsonCombinedSchemaGenerator = new JsonObjectGenerator(schema);
        typeCheck(jsonCombinedSchemaGenerator, isValidForSchema(schema))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateFromComplexEnumWithMixedTypes() {
        final ObjectSchema schema = (ObjectSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/enum-property-schema.json");
        final JsonObjectGenerator jsonCombinedSchemaGenerator = new JsonObjectGenerator(schema);
        typeCheck(jsonCombinedSchemaGenerator, isValidForSchema(schema))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateFromComplexArrayWithMixedTypes() {
        final ObjectSchema schema = (ObjectSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/array-property-schema.json");
        final JsonObjectGenerator jsonCombinedSchemaGenerator = new JsonObjectGenerator(schema);
        typeCheck(jsonCombinedSchemaGenerator, isValidForSchema(schema))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateFromComplexAnyOfScheme() {
        final ObjectSchema schema = (ObjectSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/anyOf-property-schema.json");
        final JsonObjectGenerator jsonCombinedSchemaGenerator = new JsonObjectGenerator(schema);
        typeCheck(jsonCombinedSchemaGenerator, isValidForSchema(schema))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateFromReferencedScheme() {
        final ObjectSchema schema = (ObjectSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/ref.json");
        final JsonObjectGenerator jsonCombinedSchemaGenerator = new JsonObjectGenerator(schema);
        typeCheck(jsonCombinedSchemaGenerator, isValidForSchema(schema))
                .verify(times(NUMBER_OF_TIMES));
    }
}
