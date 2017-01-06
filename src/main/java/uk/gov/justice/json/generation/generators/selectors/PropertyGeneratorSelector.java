package uk.gov.justice.json.generation.generators.selectors;

import org.apache.commons.collections.map.HashedMap;
import org.everit.json.schema.ReferenceSchema;
import org.everit.json.schema.Schema;
import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generation.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.EnumPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.selectors.arrays.ArrayGeneratorSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PropertyGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();
    private ArrayGeneratorSelector arrayGeneratorSelector = new ArrayGeneratorSelector();
    private StringPropertyGeneratorSelector stringPropertyGeneratorSelector = new StringPropertyGeneratorSelector();

    public JsonPropertyGenerator createGenerator(final String propertyName, final ReferenceSchema value) {
    final    Map<String,Object> propertyDefinitions = new HashMap<>();
//        Schema schema =value.getReferredSchema();
//        //propertyDefinitions.put("type",schema.)
        return createGenerator(propertyName,propertyDefinitions);
    }

    public JsonPropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, Object> propertyDefinitions = (Map<String, Object>) value;

        if (propertyDefinitions.containsKey("$ref")) {
            return new RefsGenertorySelector().getReferredPropertyGenerator((String) propertyDefinitions.get("$ref"));
        }

        if (propertyDefinitions.containsKey("enum")) {
            return new EnumPropertyGenerator(propertyName, (List<Object>) propertyDefinitions.get("enum"));
        }

        if (propertyDefinitions.containsKey("oneOf")) {
            return getOneOfGenerator(propertyName, (List<Object>) propertyDefinitions.get("oneOf"));
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
            case "#ref":
                return new RefsGenertorySelector().getReferredPropertyGenerator((String) propertyDefinitions.get("#ref"));

            default:
                throw new JsonGenerationException("Unknown property type '" + type + "'");
        }
    }

    private JsonPropertyGenerator getObjectGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {
        final Map<String, Object> properties = (Map<String, Object>) propertyDefinitions.get("properties");
        return selectorFactory.createNewObjectGeneratorSelector().createGenerator(propertyName, properties);
    }

    private JsonPropertyGenerator getOneOfGenerator(final String propertyName, final List<Object> schemas) {
        return new OneOfSelector().getOneOf(propertyName, schemas);
    }
}

