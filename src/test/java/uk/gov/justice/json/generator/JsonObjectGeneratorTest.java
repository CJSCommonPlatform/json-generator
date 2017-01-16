package uk.gov.justice.json.generator;

import static org.hamcrest.CoreMatchers.isA;

import uk.gov.justice.json.JsonSchemaLoader;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.json.JsonObject;

import com.jayway.jsonassert.JsonAssert;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.junit.Before;
import org.junit.Test;

public class JsonObjectGeneratorTest {

    @Test
    public void shouldGenerateFromASimpleSchema() throws IOException, URISyntaxException {
        final Schema schema = JsonSchemaLoader.loadSchema("src/test/resources/schemas/simple.object.json-schema.json");

        final JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator((ObjectSchema) schema);

        final JsonObject jsonObject = jsonObjectGenerator.next();

        JsonAssert.with(jsonObject.toString())
                .assertThat("$." + "streetAddress", isA(String.class))
                .assertThat("$." + "city", isA(String.class));

    }


}