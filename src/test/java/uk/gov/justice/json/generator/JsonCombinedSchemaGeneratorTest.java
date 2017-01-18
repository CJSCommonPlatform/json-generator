package uk.gov.justice.json.generator;

import static uk.gov.justice.json.MatcherBasedTypeCheck.typeCheck;
import static uk.gov.justice.json.generator.matcher.JsonValueSchemaMatcher.isValidForSchema;

import uk.gov.justice.json.JsonSchemaLoader;
import uk.gov.justice.json.MatcherBasedTypeCheck;

import org.everit.json.schema.CombinedSchema;
import org.junit.Test;


public class JsonCombinedSchemaGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;

    @Test(expected = JsonGenerationException.class)
    public void shouldGenerateOneOf() {
        final CombinedSchema schema = (CombinedSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/combined/one-of-schema.json");
        final JsonCombinedSchemaGenerator jsonCombinedSchemaGenerator = new JsonCombinedSchemaGenerator(schema);
    }

    @Test
    public void shouldGenerateAnyOf() {
        final CombinedSchema schema = (CombinedSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/combined/any-of-schema.json");
        final JsonCombinedSchemaGenerator jsonCombinedSchemaGenerator = new JsonCombinedSchemaGenerator(schema);
        typeCheck(jsonCombinedSchemaGenerator, isValidForSchema(schema))
                .verify(MatcherBasedTypeCheck.Times.times(NUMBER_OF_TIMES));

    }

    @Test(expected = JsonGenerationException.class)
    public void shouldGenerateAllOf() {
        final CombinedSchema schema = (CombinedSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/combined/all-of-schema.json");
        final JsonCombinedSchemaGenerator jsonCombinedSchemaGenerator = new JsonCombinedSchemaGenerator(schema);
    }
}
