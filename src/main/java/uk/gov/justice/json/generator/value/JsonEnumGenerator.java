package uk.gov.justice.json.generator.value;


import static java.lang.String.format;
import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generator.JsonObjectGenerator;
import uk.gov.justice.json.generator.JsonValueGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.Set;

import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonValue;

import com.google.gson.JsonObject;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.json.JSONObject;

public class JsonEnumGenerator extends JsonValueGenerator {


    final Set<Object> possibleValues;

    public JsonEnumGenerator(EnumSchema schema) {

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
