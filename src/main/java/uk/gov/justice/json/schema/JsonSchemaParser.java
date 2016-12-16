package uk.gov.justice.json.schema;

import static java.util.stream.Collectors.toList;

import uk.gov.justice.json.generators.factories.SimplePropertyGeneratorFactory;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;

import java.util.List;
import java.util.Map;

public class JsonSchemaParser {

    private SimplePropertyGeneratorFactory simplePropertyGeneratorFactory = new SimplePropertyGeneratorFactory();
    private Parser parser = new Parser();

    @SuppressWarnings("unchecked")
    public JsonDocumentGenerator parse(final String schema) {

        final Map<String, Object> schemaMap = parser.toMap(schema);

        final Map<String, Object> properties = (Map<String, Object>) schemaMap.get("properties");

        final List<JsonPropertyGenerator> jsonPropertyGenerators = properties
                .keySet()
                .stream()
                .filter(this::isPropertyName)
                .map(propertyName -> simplePropertyGeneratorFactory.createGenerator(propertyName, properties.get(propertyName)))
                .collect(toList());

        return new JsonDocumentGenerator(jsonPropertyGenerators);
    }

    private boolean isPropertyName(final String couldBeAPropertyName) {
        return ! (couldBeAPropertyName.equals("additionalProperties") || couldBeAPropertyName.equals("required"));
    }


}
