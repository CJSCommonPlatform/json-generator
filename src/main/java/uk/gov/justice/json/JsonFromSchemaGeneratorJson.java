package uk.gov.justice.json;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;

import java.util.Map;

import com.google.gson.Gson;

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
