package uk.gov.justice.json;

import com.google.gson.Gson;

import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;

public class JsonFromSchemaGeneratorJson implements JsonPropertyGenerator {

    private final Map schemaMap;

    public JsonFromSchemaGeneratorJson(String jsonSchema) {
        schemaMap = new Gson().fromJson(jsonSchema, Map.class);
    }

    @Override
    public String getName() {
        return null;
    }

    public String nextJson() {
        return new JsonFromSchemaMapCreator().createJson(schemaMap);
    }
}
