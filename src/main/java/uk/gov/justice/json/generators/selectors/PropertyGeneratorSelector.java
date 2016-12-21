package uk.gov.justice.json.generators.selectors;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generators.properties.EnumPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringJsonPropertyGenerator;
import uk.gov.justice.json.generators.selectors.arrays.ArrayGeneratorSelector;

import java.util.List;
import java.util.Map;

public class PropertyGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();
    private ArrayGeneratorSelector arrayGeneratorSelector = new ArrayGeneratorSelector();

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, Object> propertyDefinitions = (Map<String, Object>) value;

        final String type = (String) propertyDefinitions.get("type");
        if (propertyDefinitions.containsKey("enum")) {
            return getEnumPropertyGenerator(propertyName, propertyDefinitions);

        }

        switch (type) {
            case "string":
                return getStringTypePropertyGenerator(propertyName, propertyDefinitions);
            case "integer":
                return new IntegerJsonPropertyGenerator(propertyName);
            case "boolean":
                return new BooleanPropertyGenerator(propertyName);
            case "object":
                return getObjectTypePropertyGenerator(propertyName, propertyDefinitions);
            case "array":
                return arrayGeneratorSelector.createArrayGenerator(propertyName, propertyDefinitions);
            default:
                throw new JsonGenerationException("Unknown property type '" + type + "'");
        }
    }

    @SuppressWarnings("unchecked")
    private JsonPropertyGenerator getEnumPropertyGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {
        final List<Object> enums = (List<Object>) propertyDefinitions.get("enum");
        return new EnumPropertyGenerator(propertyName, enums);
    }

    @SuppressWarnings("unchecked")
    private JsonPropertyGenerator getStringTypePropertyGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {

        final String format = (String) propertyDefinitions.get("format");
        if (format != null) {
            if ("email".equals(format)) {
                return new EmailPropertyGenerator(propertyName);
            }
            if ("date-time".equals(format)) {
                return new IsoDateTimePropertyGenerator(propertyName);
            }
        }
        final String pattern = (String) propertyDefinitions.get("pattern");

        if (pattern != null) {
            return new RegexPropertyGenerator(propertyName, pattern);
        }

        return new StringJsonPropertyGenerator(propertyName);
    }

    @SuppressWarnings("unchecked")
    private JsonPropertyGenerator getObjectTypePropertyGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {
        final Map<String, Object> properties = (Map<String, Object>) propertyDefinitions.get("properties");
        return selectorFactory.createNewObjectGeneratorSelector().createGenerator(propertyName, properties);
    }
}

