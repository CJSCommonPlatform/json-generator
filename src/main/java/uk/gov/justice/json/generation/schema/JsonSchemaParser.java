package uk.gov.justice.json.generation.schema;

import static java.util.stream.Collectors.toList;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.selectors.PropertyGeneratorSelector;

import java.util.List;
import java.util.Map;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

public class JsonSchemaParser {

    private final PropertyGeneratorSelector propertyGeneratorSelector = new PropertyGeneratorSelector();
    private final JsonParser jsonParser = new JsonParser();

    @SuppressWarnings("unchecked")
    public JsonGenerator parse(final String schema) {

        final Map<String, Object> schemaMap = jsonParser.toMap(schema);

        final SchemaLoader schemaLoader = jsonParser.toSchemaLoader(schema);

        Schema.Builder<ObjectSchema> schemaBuilder = (Schema.Builder<ObjectSchema>) schemaLoader.load();

        final ObjectSchema objectSchema = schemaBuilder.build();

        final Map<String, Schema> properties = objectSchema.getPropertySchemas();

        final List<JsonPropertyGenerator> jsonPropertyGenerators = properties
                .keySet()
                .stream()
                .filter(this::isPropertyName)
                .map(propertyName -> propertyGeneratorSelector.createGenerator(propertyName, properties.get(propertyName)))
                .collect(toList());


        return new JsonGenerator(jsonPropertyGenerators);
    }

    private boolean isPropertyName(final String couldBeAPropertyName) {
        return !(couldBeAPropertyName.equals("additionalProperties") || couldBeAPropertyName.equals("required"));
    }
}
