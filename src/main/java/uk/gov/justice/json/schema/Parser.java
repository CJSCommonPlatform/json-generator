package uk.gov.justice.json.schema;

import java.util.Map;

import com.google.gson.Gson;

public class Parser {

    public Map<String, Object> toMap(String json) {
        return new Gson().fromJson(json, Map.class);
    }
}
