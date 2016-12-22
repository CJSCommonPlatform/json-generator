package uk.gov.justice.json.schema;

import static java.util.stream.Collectors.toList;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.selectors.PropertyGeneratorSelector;

import java.util.List;
import java.util.Map;

public class JsonSchemaParser {

    private PropertyGeneratorSelector propertyGeneratorSelector = new PropertyGeneratorSelector();
    private JsonParser jsonParser = new JsonParser();

    @SuppressWarnings("unchecked")
    public JsonDocumentGenerator parse(final String schema) {

        final Map<String, Object> schemaMap = jsonParser.toMap(schema);

        final Map<String, Object> properties = (Map<String, Object>) schemaMap.get("properties");

        final List<JsonPropertyGenerator> jsonPropertyGenerators = properties
                .keySet()
                .stream()
                .filter(this::isPropertyName)
                .map(propertyName -> propertyGeneratorSelector.createGenerator(propertyName, properties.get(propertyName)))
                .collect(toList());

        return new JsonDocumentGenerator(jsonPropertyGenerators);
    }

    private boolean isPropertyName(final String couldBeAPropertyName) {
        return ! (couldBeAPropertyName.equals("additionalProperties") || couldBeAPropertyName.equals("required"));
    }
}
