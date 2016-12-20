package uk.gov.justice.json.generators.factories;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.BooleanJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.EnumJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimeJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringJsonPropertyGenerator;

import java.util.List;
import java.util.Map;

public class SimplePropertyGeneratorFactory {

    private FactoryProvider factoryProvider;

    public SimplePropertyGeneratorFactory() {
        factoryProvider = new FactoryProvider();
    }

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, Object> propertyDefinitions = (Map<String, Object>) value;

        final String type = (String) propertyDefinitions.get("type");
        if (propertyDefinitions.containsKey("enum")) {
            final List<Object> enums = (List<Object>) propertyDefinitions.get("enum");
            return new EnumJsonPropertyGenerator(propertyName, enums);

        }

        switch (type) {
            case "string":
                return getStringTypePropertyGenerator(propertyName, propertyDefinitions);
            case "integer":
                return new IntegerJsonPropertyGenerator(propertyName);
            case "boolean":
                return new BooleanJsonPropertyGenerator(propertyName);
            case "object":
                return getObjectTypePropertyGenerator(propertyName, propertyDefinitions);
            case "array":
                return getArrayGenerator(propertyName, propertyDefinitions);
            default:
                throw new JsonGenerationException("Unknown property type '" + type + "'");
        }
    }

    private JsonPropertyGenerator getStringTypePropertyGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {

        final String format = (String) propertyDefinitions.get("format");
        if (format != null) {
            if ("email".equals(format)) {
                return new EmailJsonPropertyGenerator(propertyName);
            }
            if ("date-time".equals(format)) {
                return new IsoDateTimeJsonPropertyGenerator(propertyName);
            }
        }
        final String pattern = (String) propertyDefinitions.get("pattern");

        if (pattern != null) {
            return new RegexJsonPropertyGenerator(propertyName, pattern);
        }

        return new StringJsonPropertyGenerator(propertyName);
    }

    private JsonPropertyGenerator getObjectTypePropertyGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {
        final Object properties = propertyDefinitions.get("properties");
        return factoryProvider.createNewObjectGeneratorFactory().createGenerator(propertyName, properties);
    }

    @SuppressWarnings("unchecked")
    private JsonPropertyGenerator getArrayGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {

        final Object items = propertyDefinitions.get("items");

        // unspecifiedArrayProperty
        if (items == null) {
            return new UnspecifiedArrayPropertyGeneratorFactory().createGenerator(propertyName);
        }

        // tupleArrayProperty
        if (items instanceof List) {
            final List<Map<String, Object>> itemsList = (List<Map<String, Object>>) items;
            return new TupleArrayPropertyGeneratorFactory().createGenerator(propertyName, itemsList);
        }

        // listArrayProperty
        if (items instanceof Map) {
            final Map<String, Object> itemsMap = (Map<String, Object>) items;
            return new ListArrayPropertyGeneratorFactory().createGenerator(propertyName, itemsMap);
        }

        throw new RuntimeException("Oh dear");
    }
}

