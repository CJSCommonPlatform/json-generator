package uk.gov.justice.json.generation.generators.definitions;


import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;

import java.util.HashMap;
import java.util.Map;

public class DefinitionsProvider {

    private static final Map<String, JsonPropertyGenerator> definitions = new HashMap<>();

    private static final  String DEFINITIONS = "#/definitions/";

    public static void addPropertyGenerator(String definitionPropertyName, final JsonPropertyGenerator jsonPropertyGenerator) {
        definitions.put(DEFINITIONS+definitionPropertyName, jsonPropertyGenerator);
    }

    public static JsonPropertyGenerator getJsonPropertyGenerator(final String definitionName) {
        return definitions.get(definitionName);
    }

    public Map<String, JsonPropertyGenerator> getDefinitions(){
        return definitions;
    }
}
