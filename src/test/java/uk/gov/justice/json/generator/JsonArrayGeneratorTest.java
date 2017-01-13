package uk.gov.justice.json.generator;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.JsonSchemaLoader;

import javax.json.JsonArray;

import org.everit.json.schema.ArraySchema;
import org.junit.Test;

/**
 * Created by david on 12/01/17.
 */
public class JsonArrayGeneratorTest {

    @Test
    public void shouldGenerateSimpleStringArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/strings-schema.json");
        JsonArrayGenerator generator = new JsonArrayGenerator(schema);
        JsonArray array = generator.next();
        assertThat(array.get(0), notNullValue());
    }
}
