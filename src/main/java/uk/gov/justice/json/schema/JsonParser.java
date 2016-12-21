package uk.gov.justice.json.schema;

import java.util.Map;

import com.google.gson.Gson;

public class JsonParser {

    private final Gson gson = new Gson();

    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap(String json) {
        return gson.fromJson(json, Map.class);
    }
}
