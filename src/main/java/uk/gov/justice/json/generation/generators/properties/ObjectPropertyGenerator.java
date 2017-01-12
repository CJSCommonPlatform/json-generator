package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.selectors.PropertyGeneratorSelector;
import uk.gov.justice.json.generation.generators.values.ObjectValueGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;

public class ObjectPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final Schema schema;
    private final PropertyGeneratorSelector selector;

    private final JsonPropertyFormatter jsonPropertyFormatter = new JsonPropertyFormatter();

    public ObjectPropertyGenerator(
            final String name,
            final Schema schema, PropertyGeneratorSelector selector) {
        this.name = name;
        this.schema = schema;
        this.selector = selector;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String nextJson() {
        final Map<String, Schema> propertySchemas = ((ObjectSchema) schema).getPropertySchemas();
        final List<JsonPropertyGenerator> propertyGenerators = new ArrayList<>();
        propertyGenerators.add(new RootObjectPropertyGenerator(name));
        propertySchemas.forEach((propertyName, schema) -> propertyGenerators.add(createGenerator(propertyName, schema)));
        return new ObjectValueGenerator(propertyGenerators).nextValue();
    }

    private JsonPropertyGenerator createGenerator(String propertyName, Schema schema) {
        return selector.createGenerator(propertyName, schema);
    }
}
