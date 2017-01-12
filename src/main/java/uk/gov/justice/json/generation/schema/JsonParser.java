package uk.gov.justice.json.generation.schema;

import java.util.Map;

import com.google.gson.Gson;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonParser {

    private final Gson gson = new Gson();

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap(String json) {
        return gson.fromJson(json, Map.class);
    }

    public SchemaLoader toSchemaLoader(final String json) {
        JSONObject jsonSchema = new JSONObject(new JSONTokener(json));

        return SchemaLoader.builder()
                .schemaJson(jsonSchema)
                .build();

    }

}
