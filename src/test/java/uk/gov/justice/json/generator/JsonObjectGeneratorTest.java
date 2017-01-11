package uk.gov.justice.json.generator;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.CoreMatchers.isA;

import uk.gov.justice.json.generation.schema.JsonParser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.json.JsonObject;

import com.jayway.jsonassert.JsonAssert;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.junit.Before;
import org.junit.Test;

public class JsonObjectGeneratorTest {


    @Before
    public void init() {


    }

    @Test
    public void shouldGenerateFromASimpleSchema() throws IOException, URISyntaxException {

        Schema schema = getFileAsSchema("/simple.json-schema.json");

        JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator((ObjectSchema) schema);

        final JsonObject jsonObject = jsonObjectGenerator.next();

        JsonAssert.with(jsonObject.toString())
                .assertThat("$." + "streetAddress", isA(String.class))
                .assertThat("$." + "city", isA(String.class));

    }


    private Schema getFileAsSchema(final String filePath) throws URISyntaxException, IOException {
        final JsonParser jsonParser = new JsonParser();
        final SchemaLoader schemaLoader = jsonParser.toSchemaLoader(readFileToString(new File(this.getClass().getResource(filePath).toURI())));
        return schemaLoader.load().build();
    }


}