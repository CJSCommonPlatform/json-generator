package uk.gov.justice.json.generator;

import uk.gov.justice.json.generation.JsonGenerationException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;

/**
 * Created by david on 10/01/17.
 */
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

    // TODO: Randomise non-mandatory fields
    @Override
    public JsonObject nextValue() {
        Set<String> properties = generators.keySet();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for(String property : properties) {
            builder = builder.add(property, generators.get(property).nextValue());
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
