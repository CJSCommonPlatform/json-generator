package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generation.JsonGenerationException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;

public class JsonObjectGenerator implements JsonValueGenerator {

    private Map<String, JsonValueGenerator> generators;
    private Set<String> properties;
    private Set<String> requiredProperties;


    public JsonObjectGenerator(final ObjectSchema schema) {
        properties = schema.getPropertySchemas().keySet();
        requiredProperties = new HashSet<>(schema.getRequiredProperties());

        generators = new HashMap<>();
        Map<String, Schema> propertySchemas = schema.getPropertySchemas();
        for(String property : propertySchemas.keySet()) {
            generators.put(property, generatorFor(propertySchemas.get(property)));
        }
    }

    @Override
    public JsonObject nextValue() {

        final Set<String> properties = generators.keySet();
        final JsonObjectBuilder builder = createObjectBuilder();

        for(String property : properties) {
            JsonValue jsonValue;

            if(requiredProperties.contains(property)) {
                jsonValue = generators.get(property).nextValue();
                builder.add(property, jsonValue);
            } else {
                Random rn = new Random();
                if(rn.nextBoolean()) {
                    jsonValue = generators.get(property).nextValue();
                    builder.add(property, jsonValue);
                }
            }
        }

        return builder.build();
    }

    private JsonValueGenerator generatorFor(final Schema schema) {
        switch (schema.getClass().getSimpleName()) {
            case "ObjectSchema":
                return new JsonObjectGenerator((ObjectSchema) schema);
//            case "ReferenceSchema":
//                return createGenerator(propertyName, ((ReferenceSchema) schema).getReferredSchema());
            case "StringSchema":
                return new JsonStringGenerator((StringSchema) schema);
//            case "IntegerSchema":
//                return new IntegerPropertyGenerator(propertyName);
//            case "BooleanSchema":
//                return new BooleanPropertyGenerator(propertyName);
//            case "NumberSchema":
//                return new IntegerPropertyGenerator(propertyName);
//            case "ArraySchema":
//                return arrayGeneratorSelector.getArrayGenerator(propertyName, (ArraySchema) schema);
//            case "EnumSchema":
//                Set<Object> values = ((EnumSchema) schema).getPossibleValues();
//                return new EnumPropertyGenerator(propertyName, values);
//            case "CombinedSchema":
//                return new OneOfSelector().getOneOf(propertyName, (CombinedSchema) schema);
            default:
                throw new JsonGenerationException("Unknown property type '" + schema.getClass().getSimpleName() + "'");
        }
    }
}
