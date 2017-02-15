package uk.gov.justice.json.generator;

import uk.gov.justice.json.JsonSchemaLoader;

import org.everit.json.schema.NotSchema;
import org.junit.Test;

public class JsonNotSchemaGeneratorTest {

    @Test(expected = JsonGenerationException.class)
    public void shouldGenerateNotSchema() {
        final NotSchema schema = (NotSchema) JsonSchemaLoader
                .loadSchema("src/test/resources/schemas/combined/not-schema.json");
        final JsonNotSchemaGenerator jsonNotSchemaGenerator = new JsonNotSchemaGenerator(schema);
    }
}