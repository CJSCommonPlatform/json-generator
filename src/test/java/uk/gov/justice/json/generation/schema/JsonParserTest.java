package uk.gov.justice.json.generation.schema;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.FileLoader;

import java.util.Map;

import org.junit.Test;

public class JsonParserTest {

    private final JsonParser jsonParser = new JsonParser();
    private final FileLoader fileLoader = new FileLoader();

    @Test
    public void shouldParseAJsonDocumentToAString() throws Exception {

        final String jsonSchema = fileLoader.loadAsJsonSting("src/test/resources/simple-property-schema.json");
        final Map<String, Object> objectMap = jsonParser.toMap(jsonSchema);

        assertThat(objectMap.get("$schema"), is("http://json-schema.org/draft-04/schema#"));
        assertThat(objectMap.get("id"), is("/"));
        assertThat(objectMap.get("type"), is("object"));
        assertThat(objectMap.containsKey("properties"), is(true));
        assertThat(((Map) objectMap.get("properties")).size(), is(6));
        assertThat(objectMap.get("additionalProperties"), is(false));
        assertThat(objectMap.containsKey("required"), is(true));
        assertThat(((Map) objectMap.get("properties")).size(), is(6));
    }
}
