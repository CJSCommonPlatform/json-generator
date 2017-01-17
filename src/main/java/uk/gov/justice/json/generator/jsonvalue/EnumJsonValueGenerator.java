package uk.gov.justice.json.generator.jsonvalue;

import static java.lang.String.format;
import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.JsonGenerationException;
import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.Random;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.everit.json.schema.EnumSchema;
import org.json.JSONObject;

public class EnumJsonValueGenerator extends Generator<JsonValue> {

    private final Set<Object> possibleValues;

    public EnumJsonValueGenerator(EnumSchema schema) {
        possibleValues = schema.getPossibleValues();
    }

    @Override
    public JsonValue next() {

        final Object value = randomlyGet(possibleValues);

        if (value.equals(JSONObject.NULL)) {
            return JsonValue.NULL;
        }

        if (value instanceof String) {
            return constructJsonString((String) value);
        }

        if (value instanceof Integer) {
            return createObjectBuilder().add("tmp", (Integer)value).build().getJsonNumber("tmp");
        }

        if (value instanceof Double) {
            return createObjectBuilder().add("tmp", (Double)value).build().getJsonNumber("tmp");
        }

        if (value instanceof Boolean) {
            if (value.equals(Boolean.TRUE)) {
                return JsonValue.TRUE;
            }
            return JsonValue.FALSE;
        }
         throw new JsonGenerationException(format("Unsupported Type: %s",value ));

    }

    private Object randomlyGet(Set<Object> possibleValues) {
        final int size = possibleValues.size();
        Random random = new Random();
        final int value =random.nextInt(size-1 - 0 + 1) + 0 ;
        final Object[] possibleValuesList = possibleValues.toArray();
        return possibleValuesList[value];
    }

    private JsonString constructJsonString(final String string) {
        return createObjectBuilder().add("tmp", string).build().getJsonString("tmp");
    }
}
