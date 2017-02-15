package uk.gov.justice.json.generator.jsonvalue;

import static java.lang.String.format;
import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonNumber;
import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonString;

import uk.gov.justice.json.generator.JsonGenerationException;
import uk.gov.justice.services.test.utils.core.random.Generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonValue;

import org.everit.json.schema.EnumSchema;
import org.json.JSONObject;

public class EnumJsonValueGenerator extends Generator<JsonValue> {

    private final List<Object> possibleValues;

    public EnumJsonValueGenerator(final EnumSchema schema) {
        possibleValues = new ArrayList<>(schema.getPossibleValues());
    }

    @Override
    public JsonValue next() {

        final int index = RANDOM.nextInt(possibleValues.size());

        final Object value = possibleValues.get(index);

        if (value.equals(JSONObject.NULL)) {
            return JsonValue.NULL;
        }

        if (value instanceof String) {
            return buildJsonString((String) value);
        }

        if (value instanceof Integer) {
            return buildJsonNumber((Integer) value);
        }

        if (value instanceof Double) {
            return buildJsonNumber(BigDecimal.valueOf((Double) value));
        }

        if (value instanceof Boolean) {
            if (value.equals(Boolean.TRUE)) {
                return JsonValue.TRUE;
            }
            return JsonValue.FALSE;
        }

        throw new JsonGenerationException(format("Unsupported Type: %s",value ));
    }
}
