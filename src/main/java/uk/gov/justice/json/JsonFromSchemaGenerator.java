package uk.gov.justice.json;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.mifmif.common.regex.Generex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.google.common.base.Charsets.UTF_8;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * Generates random json from a given JSON schema location
 */
class JsonFromSchemaGenerator extends Generator<String> {

    private final Gson gson = new Gson();
    private final Map rootJsonObj;
    private Random random = new Random();

    JsonFromSchemaGenerator(String pathToJsonSchema) {
        rootJsonObj = gson.fromJson(getSchemaContent(pathToJsonSchema), Map.class);
    }

    public String next() {
        return processSchemaJsonObjectForType(rootJsonObj);
    }

    private String getJsonForDefinition(String definitionKey) {
        final Map definitions = (Map) rootJsonObj.get("definitions");
        final Map definition = (Map) definitions.get(definitionKey);
        return processSchemaJsonObjectForType(definition);
    }

    private String processSchemaJsonObjectForType(Map jsonObj) {

        if (jsonObj.containsKey("$ref")) {
            return processSchemaJsonObjectForRef(jsonObj);
        }

        StringBuilder sb = new StringBuilder();

        Object typeObj = null;
        if (jsonObj.containsKey("type")) {
            typeObj = jsonObj.get("type");
        } else {
            sb.append(processObject(jsonObj));
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
                    sb.append(processObject(jsonObj));
                    break;
                case "boolean":
                    sb.append(processBoolean(jsonObj));
                    break;
                case "string":
                    sb.append(processString(jsonObj));
                    break;
                case "integer":
                case "number":
                    sb.append(processInteger(jsonObj));
                    break;
                case "array":
                    sb.append(processArray(jsonObj));
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

    private String anyType(List<String> types) {
        int index = RANDOM.nextInt(types.size());
        return types.get(index);
    }

    private String processObject(Map jsonObj) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        if (jsonObj.containsKey("required")) {
            final List<String> required = (List<String>) jsonObj.get("required");

            if (!required.isEmpty()) {
                assert (jsonObj.containsKey("properties"));
                final Map childJsonObj = (Map) jsonObj.get("properties");
                 required.forEach(s -> {
                    assert childJsonObj.containsKey(s);
                });

            }
        }

        if (jsonObj.containsKey("oneOf")) {
            final List oneOf = (List) jsonObj.get("oneOf");
            return processObject((Map) oneOf.get(random.nextInt(oneOf.size())));
        }

        if (jsonObj.containsKey("anyOf")) {
            final List anyOf = (List) jsonObj.get("anyOf");
            return processObject((Map) anyOf.get(random.nextInt(anyOf.size())));
        }

        Map childJsonObj = (Map) jsonObj.get("properties");
        //TODO generate all required fields and optional fields :)
        if (childJsonObj != null) {
            sb.append(childJsonObj.keySet().stream().map(key -> format("\"%s\":", key) + processValue(childJsonObj, key)).collect(joining(", ")));
        }

        sb.append('}');


        return sb.toString();
    }

    private String processValue(Map childJsonObj, Object key) {
        final Map value = (Map) childJsonObj.get(key);
        if (value.containsKey("enum")) {
            return processSchemaJsonObjectForEnum(value);
        } else if (value.containsKey("type")) {
            return processSchemaJsonObjectForType(value);
        } else if (value.containsKey("$ref")) {
            return processSchemaJsonObjectForRef(value);
        } else {
            return "";
        }
    }

    private String processArray(Map jsonObj) {
        Boolean uniqueItems = null;
        if (jsonObj.containsKey("uniqueItems")) {
            uniqueItems = (Boolean) jsonObj.get("uniqueItems");
        }
        Integer minItems = null;
        if (jsonObj.containsKey("minItems")) {
            minItems = ((Double) jsonObj.get("minItems")).intValue();
        }

        final Object items;
        if (jsonObj.containsKey("items")) {
            items = jsonObj.get("items");
        } else if (jsonObj.containsKey("properties")) {
            items = jsonObj.get("properties");
        } else {
            throw new RuntimeException("Unknown Array Type:");
        }

        String arrayValue;

        if (items instanceof Map) {
            arrayValue = processSchemaJsonObjectForType((Map) items);
        } else if (items instanceof List) {
            List<Map> listOfItems = (List<Map>) items;
            arrayValue = listOfItems.stream().map(this::processObject).collect(joining(", "));

        } else {
            throw new RuntimeException("Unknown Array Type:" + items);
        }

        return format("[%s]", arrayValue);

    }

    private Integer processInteger(Map jsonObj) {
        int minimum = Integer.MIN_VALUE, maximum = Integer.MAX_VALUE;

        if (jsonObj.containsKey("minimum")) {
            minimum = ((Double) jsonObj.get("minimum")).intValue();
        }
        if (jsonObj.containsKey("maximum")) {
            maximum = ((Double) jsonObj.get("maximum")).intValue();
        }
        return new IntegerGenerator(minimum, maximum).next();
    }

    private String processString(Map jsonObj) {
        String value;
        if (jsonObj.containsKey("pattern")) {
            String pattern = (String) jsonObj.get("pattern");
            //TODO fix the pattern properly
            pattern = pattern.replace('^', ' ').replace('$', ' ').trim();
            value = new Generex(pattern).random();
        } else if (jsonObj.containsKey("format")) {
            String format = (String) jsonObj.get("format");
            if (format.equals("email")) {
                value = randomAlphanumeric(5) + "@test.com";
            } else if (format.equals("date-time")) {
                value = ZonedDateTime.now().format(ISO_DATE_TIME);
            } else {
                value = randomAlphanumeric(10);
            }
        } else {
            value = randomAlphanumeric(10);
        }
        return format("\"%s\"", value);
    }

    private boolean processBoolean(Map jsonObj) {
        return random.nextBoolean();
    }

    private String processSchemaJsonObjectForEnum(Map jsonObj) {
        final Object anEnum = jsonObj.get("enum");
        List<String> items = (List<String>) anEnum;

        final Object next1 = items.get(random.nextInt(items.size()));
        if (next1 instanceof Boolean) {
            return next1.toString();
        }
        final String next = (String) next1;
        if (next.equalsIgnoreCase("true") || next.equalsIgnoreCase("false")) {
            return next;
        }
        return format("\"%s\"", next);
    }

    private String processSchemaJsonObjectForRef(Map jsonObj) {
        final String ref = (String) jsonObj.get("$ref");
        final String definitionKey = ref.replace("#/definitions/", "");
        return getJsonForDefinition(definitionKey);
    }

    private String getSchemaContent(String pathToJsonSchema) {
        String schemaContent;
        try {
            if (Paths.get(pathToJsonSchema).isAbsolute()) {
                schemaContent = Files.toString(new File(pathToJsonSchema), UTF_8);
            } else {
                schemaContent = Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return schemaContent;
    }
}
