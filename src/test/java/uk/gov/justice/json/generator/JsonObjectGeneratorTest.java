package uk.gov.justice.json.generator;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.CoreMatchers.isA;
import static uk.gov.justice.json.generator.JsonFileHelper.getInstance;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.json.JsonObject;

import com.jayway.jsonassert.JsonAssert;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.junit.Before;
import org.junit.Test;

public class JsonObjectGeneratorTest {


    @Before
    public void init() {


    }

    @Test
    public void shouldGenerateFromASimpleSchema() throws IOException, URISyntaxException {

        Schema schema = getInstance().getFileAsSchema("/simple.json-schema.json");

        JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator((ObjectSchema) schema);

        final JsonObject jsonObject = jsonObjectGenerator.next();

        JsonAssert.with(jsonObject.toString())
                .assertThat("$." + "streetAddress", isA(String.class))
                .assertThat("$." + "city", isA(String.class));

    }


}