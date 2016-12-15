package uk.gov.justice.json;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class JsonFromSchemaMapCreator {

    private final Random random = new Random();

    private final JsonValueCreator jsonValueCreator = new JsonValueCreator();
    private final JsonStringCreator jsonStringCreator = new JsonStringCreator();

    @SuppressWarnings("unchecked")
    public String createJson(final Map schemaMap) {

        if (schemaMap.containsKey("$ref")) {
            return processSchemaJsonObjectForRef(schemaMap);
        }

        final StringBuilder sb = new StringBuilder();

        final Object typeObj;
        if (schemaMap.containsKey("type")) {
            typeObj = schemaMap.get("type");
        } else {
            typeObj = null;
            sb.append(jsonValueCreator.createJsonObject(schemaMap));
        }

        String type;

        if (typeObj instanceof List) {
            type = anyType((List<String>) typeObj);
        } else {
            type = (String) typeObj;
        }
        if (type != null) {
            switch (type) {
                case "object":
                    sb.append(jsonValueCreator.createJsonObject(schemaMap));
                    break;
                case "boolean":
                    sb.append(jsonValueCreator.createRandomBoolean());
                    break;
                case "string":
                    sb.append(jsonStringCreator.createRandomString(schemaMap));
                    break;
                case "integer":
                case "number":
                    sb.append(jsonValueCreator.createRandomInteger(schemaMap));
                    break;
                case "array":
                    sb.append(jsonValueCreator.createJsonArray(schemaMap));
                    break;
                case "null":
                    sb.append("null");
                    break;
                default:
                    throw new RuntimeException("Unsupported JSON Schema Type:" + type);
            }
        }

        return sb.toString();
    }

    private String processSchemaJsonObjectForRef(final Map schemaMap) {
        final String ref = (String) schemaMap.get("$ref");
        final String definitionKey = ref.replace("#/definitions/", "");
        return getJsonForDefinition(definitionKey, schemaMap);
    }

    private String anyType(List<String> types) {
        int index = random.nextInt(types.size());
        return types.get(index);
    }

    private String getJsonForDefinition(final String definitionKey, final Map schemaMap) {
        final Map definitions = (Map) schemaMap.get("definitions");
        final Map definition = (Map) definitions.get(definitionKey);
        return new JsonFromSchemaMapCreator().createJson(definition);
    }
}
