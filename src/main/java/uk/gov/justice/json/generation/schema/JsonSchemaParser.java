package uk.gov.justice.json.generation.schema;

import static java.util.stream.Collectors.toList;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import uk.gov.justice.json.generation.generators.definitions.DefinitionsProvider;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.selectors.PropertyGeneratorSelector;

import java.util.List;
import java.util.Map;

public class JsonSchemaParser {

    private final PropertyGeneratorSelector propertyGeneratorSelector = new PropertyGeneratorSelector();
    private final JsonParser jsonParser = new JsonParser();

    @SuppressWarnings("unchecked")
    public JsonGenerator parse(final String schema) {

//        final Map<String, Object> schemaMap = jsonParser.toMap(schema);
//
//        final SchemaLoader schemaLoader = jsonParser.toSchemaLoader(schema);
//
//        Schema.Builder<ObjectSchema> schemaBuilder = (Schema.Builder<ObjectSchema>) schemaLoader.load();
//
//        final ObjectSchema objectSchema = schemaBuilder.build();


       // final Map<String, Schema> properties =    objectSchema.getPropertySchemas();

        final Map<String, Object> schemaMap = jsonParser.toMap(schema);


        final Map<String, Object> properties = (Map<String, Object>) schemaMap.get("properties");

        final Map<String, Object> definitions = (Map<String, Object>) schemaMap.get("definitions");

        definitions
                .keySet()
                .stream()
                .filter(this::isPropertyName)
                .map(propertyName -> {
                    JsonPropertyGenerator propertyGenerator =propertyGeneratorSelector.createGenerator(propertyName, definitions.get(propertyName));
                    DefinitionsProvider.addPropertyGenerator(propertyName,propertyGenerator);
                    return propertyGenerator;
                })
                .collect(toList());

        final List<JsonPropertyGenerator> jsonPropertyGenerators = properties
                .keySet()
                .stream()
                .filter(this::isPropertyName)
                .map(propertyName -> propertyGeneratorSelector.createGenerator(propertyName, properties.get(propertyName)))
                .collect(toList());


        return new JsonGenerator(jsonPropertyGenerators);
    }

    private boolean isPropertyName(final String couldBeAPropertyName) {
        return ! (couldBeAPropertyName.equals("additionalProperties") || couldBeAPropertyName.equals("required"));
    }
}
