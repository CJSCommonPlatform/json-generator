package uk.gov.justice.json.schema;

import static java.util.stream.Collectors.toList;

import uk.gov.justice.json.generators.factories.SimplePropertyGeneratorFactory;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;

import java.util.List;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;

public class JsonSchemaParser {

    private final SimplePropertyGeneratorFactory simplePropertyGeneratorFactory;

    public JsonSchemaParser() {
        this(new SimplePropertyGeneratorFactory());
    }

    @VisibleForTesting
    JsonSchemaParser(final SimplePropertyGeneratorFactory simplePropertyGeneratorFactory) {
        this.simplePropertyGeneratorFactory = simplePropertyGeneratorFactory;
    }

    @SuppressWarnings("unchecked")
    public JsonDocumentGenerator parse(final String schema) {

        final Map schemaMap = new Gson().fromJson(schema, Map.class);

        final Map<String, Object> properties = (Map<String, Object>) schemaMap.get("properties");

        final List<JsonPropertyGenerator> jsonPropertyGenerators = properties
                .keySet()
                .stream()
                .filter(propertyName -> ! propertyName.equals("additionalProperties"))
                .filter(propertyName -> ! propertyName.equals("required"))
                .map(propertyName -> simplePropertyGeneratorFactory.createGenerator(propertyName, properties.get(propertyName)))
                .collect(toList());

        return new JsonDocumentGenerator(jsonPropertyGenerators);
    }



}
