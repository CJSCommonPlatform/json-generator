package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generation.JsonGenerationException;

import java.math.BigDecimal;

import javax.json.JsonNumber;
import javax.json.JsonString;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;

/**
 * Helper utilities for JsonValue generators.
 */
public final class JsonValueGenerators {

    /**
     * Private constructor to prevent misuse.
     */
    private JsonValueGenerators() {
    }

    public static JsonValueGenerator generatorFor(final Schema schema) {
        switch (schema.getClass().getSimpleName()) {
            case "ObjectSchema":
                return new JsonObjectGenerator((ObjectSchema) schema);
            case "StringSchema":
                return new JsonStringGenerator((StringSchema) schema);
            case "NumberSchema":
                return new JsonNumberGenerator((NumberSchema) schema);

//            case "ReferenceSchema":
//                return createGenerator(propertyName, ((ReferenceSchema) schema).getReferredSchema());
            case "BooleanSchema":
                return new JsonBooleanGenerator();
            case "ArraySchema":
                return new JsonArrayGenerator((ArraySchema) schema);
//            case "EnumSchema":
//                return new EnumGenerator((EnumSchema) schema);
//            case "CombinedSchema":
//                return new OneOfSelector().getOneOf(propertyName, (CombinedSchema) schema);
            default:
                throw new JsonGenerationException("Unknown schema type '" + schema.getClass().getSimpleName() + "'");
        }
    }

    /**
     * Construct a JsonString from a String. The javax.json API does not have an interface for
     * doing this nicely, so we have to bodge it.
     * @param string the value
     * @return a JsonString with the given value
     */
    public static JsonString buildJsonString(final String string) {
        final String propertyName = "PROPERTY_NAME";
        return createObjectBuilder().add(propertyName, string).build().getJsonString(propertyName);
    }

    /**
     * Construct a JsonNumber from an Integer. The javax.json API does not have an interface for
     * doing this nicely, so we have to bodge it.
     * @param integer the value
     * @return a JsonNumber with the given value
     */
    public static JsonNumber buildJsonNumber(final Integer integer) {
        final String propertyName = "PROPERTY_NAME";
        return createObjectBuilder().add(propertyName, integer).build().getJsonNumber(propertyName);
    }

    /**
     * Construct a JsonNumber from a BigDecimal. The javax.json API does not have an interface for
     * doing this nicely, so we have to bodge it.
     * @param bigDecimal the value
     * @return a JsonNumber with the given value
     */
    public static JsonNumber buildJsonNumber(final BigDecimal bigDecimal) {
        final String propertyName = "PROPERTY_NAME";
        return createObjectBuilder().add(propertyName, bigDecimal).build().getJsonNumber(propertyName);
    }
}
