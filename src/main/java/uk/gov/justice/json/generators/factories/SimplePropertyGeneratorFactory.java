package uk.gov.justice.json.generators.factories;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.BooleanJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimeJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringJsonPropertyGenerator;

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

        switch (type) {
            case "string":
                return getStringTypePropertyGenerator(propertyName, propertyDefinitions);
            case "integer":
                return new IntegerJsonPropertyGenerator(propertyName);
            case "boolean":
                return new BooleanJsonPropertyGenerator(propertyName);
            case "object":
                return getObjectTypePropertyGenerator(propertyName, propertyDefinitions);
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
}
