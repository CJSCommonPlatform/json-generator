package uk.gov.justice.json.generators.selectors;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generators.properties.EnumPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.selectors.arrays.ArrayGeneratorSelector;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PropertyGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();
    private ArrayGeneratorSelector arrayGeneratorSelector = new ArrayGeneratorSelector();
    private StringPropertyGeneratorSelector stringPropertyGeneratorSelector = new StringPropertyGeneratorSelector();

    public JsonPropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, Object> propertyDefinitions = (Map<String, Object>) value;

        if (propertyDefinitions.containsKey("enum")) {
            return new EnumPropertyGenerator(propertyName, (List<Object>) propertyDefinitions.get("enum"));
        }

        final String type = (String) propertyDefinitions.get("type");

        switch (type) {
            case "string":
                return stringPropertyGeneratorSelector.getStringPropertyGenerator(propertyName, propertyDefinitions);
            case "integer":
                return new IntegerPropertyGenerator(propertyName);
            case "boolean":
                return new BooleanPropertyGenerator(propertyName);
            case "object":
                return getObjectGenerator(propertyName, propertyDefinitions);
            case "array":
                return arrayGeneratorSelector.getArrayGenerator(propertyName, propertyDefinitions);
            default:
                throw new JsonGenerationException("Unknown property type '" + type + "'");
        }
    }

    private JsonPropertyGenerator getObjectGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {
        final Map<String, Object> properties = (Map<String, Object>) propertyDefinitions.get("properties");
        return selectorFactory.createNewObjectGeneratorSelector().createGenerator(propertyName, properties);
    }
}

