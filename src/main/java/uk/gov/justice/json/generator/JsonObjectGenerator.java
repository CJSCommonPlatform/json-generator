package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;

public class JsonObjectGenerator extends JsonValueGenerator<JsonObject> {

    private Map<String, JsonValueGenerator> generators;
    private Set<String> properties;
    private Set<String> requiredProperties;

    public JsonObjectGenerator(final ObjectSchema schema) {
        properties = schema.getPropertySchemas().keySet();
        requiredProperties = new HashSet<>(schema.getRequiredProperties());

        generators = new HashMap<>();
        Map<String, Schema> propertySchemas = schema.getPropertySchemas();

        for(final String property : propertySchemas.keySet()) {
            generators.put(property, generatorFor(propertySchemas.get(property)));
        }
    }

    @Override
    public JsonObject next() {

        final JsonObjectBuilder builder = createObjectBuilder();

        for (final String property : properties) {
            if (requiredProperties.contains(property)) {
                builder.add(property, generators.get(property).next());
            } else {
                if (RANDOM.nextBoolean()) {
                    builder.add(property, generators.get(property).next());
                }
            }
        }

        return builder.build();
    }
}
