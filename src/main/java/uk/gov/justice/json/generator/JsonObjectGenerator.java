package uk.gov.justice.json.generator;

import static java.util.stream.Collectors.toMap;
import static javax.json.Json.createObjectBuilder;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;

public class JsonObjectGenerator extends Generator<JsonValue> {

    private final Map<String, Generator<? extends JsonValue>> generators;
    private final Set<String> properties;
    private final Set<String> requiredProperties;

    public JsonObjectGenerator(final ObjectSchema schema) {
        final Map<String, Schema> propertySchemas = schema.getPropertySchemas();

        properties = propertySchemas.keySet();
        requiredProperties = new HashSet<>(schema.getRequiredProperties());

        generators = propertySchemas.entrySet().stream()
                .collect(toMap(Entry::getKey, entry -> generatorFor(entry.getValue())));
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
