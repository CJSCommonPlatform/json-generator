package uk.gov.justice.json.generator;

import static javax.json.JsonValue.ValueType.NUMBER;
import static javax.json.JsonValue.ValueType.STRING;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MAX_ITEMS;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MIN_ITEMS;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.Times.times;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.typeCheck;

import uk.gov.justice.json.JsonSchemaLoader;

import javax.json.JsonArray;
import javax.json.JsonValue;

import org.everit.json.schema.ArraySchema;
import org.junit.Test;

/**
 * Unit tests for the {@link JsonArrayGenerator} class.
 */
public class JsonArrayGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;

    @Test
    public void shouldGenerateStringsArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/strings-schema.json");
        final JsonArrayGenerator generator = new JsonArrayGenerator(schema);

        typeCheck(generator, this::isAllStrings)
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateTupleArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/tuple-schema.json");
        final JsonArrayGenerator generator = new JsonArrayGenerator(schema);

        typeCheck(generator, array -> {
            if (array.size() > 0 && !NUMBER.equals(array.get(0).getValueType())) {
                return false;
            }
            if (array.size() > 1 && !STRING.equals(array.get(1).getValueType())) {
                return false;
            }
            return true;
        }).verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateUnboundedArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/strings-schema.json");
        final JsonArrayGenerator generator = new JsonArrayGenerator(schema);

        typeCheck(generator, array -> array.size() >= DEFAULT_MIN_ITEMS && array.size() <= DEFAULT_MAX_ITEMS)
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateBoundedArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/bounded-strings-schema.json");
        final JsonArrayGenerator generator = new JsonArrayGenerator(schema);

        typeCheck(generator, array -> array.size() >= 3 && array.size() <= 7)
                .verify(times(NUMBER_OF_TIMES));
    }

    private boolean isAllStrings(JsonArray array) {
        for(JsonValue value : array.getValuesAs(JsonValue.class)) {
            if (!STRING.equals(value.getValueType())) {
                return false;
            }
        }
        return true;
    }
}
