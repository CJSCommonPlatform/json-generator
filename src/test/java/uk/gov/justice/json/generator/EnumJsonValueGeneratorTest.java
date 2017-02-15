package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;
import static uk.gov.justice.json.JsonSchemaLoader.loadSchema;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.Times.times;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.typeCheck;

import uk.gov.justice.json.JsonSchemaLoader;
import uk.gov.justice.json.generator.jsonvalue.EnumJsonValueGenerator;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.Schema;
import org.junit.Test;


public class EnumJsonValueGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;

    @Test
    public void shouldGenerateStringValue() throws IOException, URISyntaxException {

        final Schema schema = loadSchema("src/test/resources/schemas/enums/simple.enum.json-schema.json");

        final EnumJsonValueGenerator jsonEnumGenerator = new EnumJsonValueGenerator((EnumSchema) schema);

        typeCheck(jsonEnumGenerator, jsonValue -> jsonValue.equals(constructJsonString("green"))|| jsonValue.equals(constructJsonString("amber")))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateNullValue() {

        final Schema schema = loadSchema("src/test/resources/schemas/enums/null.enum.json-schema.json");

        final EnumJsonValueGenerator jsonEnumGenerator = new EnumJsonValueGenerator((EnumSchema) schema);

        typeCheck(jsonEnumGenerator, jsonValue -> jsonValue.equals(JsonValue.NULL)
                || jsonValue.equals(constructJsonNumber(3))
                || jsonValue.equals(constructJsonNumber(1)))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateIntegerValue() {

        final Schema schema = loadSchema("src/test/resources/schemas/enums/integer.enum.json-schema.json");

        final EnumJsonValueGenerator jsonEnumGenerator = new EnumJsonValueGenerator((EnumSchema) schema);

        typeCheck(jsonEnumGenerator, jsonValue -> jsonValue.equals(constructJsonNumber(1))
                || jsonValue.equals(constructJsonNumber(2))
                || jsonValue.equals(constructJsonNumber(3)))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateDecimalValue() {

        final Schema schema = loadSchema("src/test/resources/schemas/enums/number.enum.json-schema.json");

        final EnumJsonValueGenerator jsonEnumGenerator = new EnumJsonValueGenerator((EnumSchema) schema);

        typeCheck(jsonEnumGenerator, jsonValue -> jsonValue.equals(constructJsonNumber(1.0662))
                || jsonValue.equals(constructJsonNumber(20000000))
                || jsonValue.equals(constructJsonNumber(0.124)))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateFromEnumWithMixedTypes() {

        final Schema schema = loadSchema("src/test/resources/schemas/enums/mixed.enum.json-schema.json");

        final EnumJsonValueGenerator jsonEnumGenerator = new EnumJsonValueGenerator((EnumSchema) schema);

        typeCheck(jsonEnumGenerator, jsonValue -> jsonValue.equals(constructJsonNumber(1.0662))
                || jsonValue.equals(constructJsonString("string"))
                || jsonValue.equals(constructJsonNumber(0.124))
                || jsonValue.equals(constructJsonNumber(123)))
                .verify(times(NUMBER_OF_TIMES));

    }

    private JsonString constructJsonString(final String string) {
        return createObjectBuilder().add("tmp", string).build().getJsonString("tmp");
    }

    private JsonNumber constructJsonNumber(final Number number) {

        if (number instanceof Integer) {
            return createObjectBuilder().add("tmp",(Integer) number).build().getJsonNumber("tmp");
        }
        if (number instanceof Double){
            return createObjectBuilder().add("tmp", (Double)number).build().getJsonNumber("tmp");
        }
        return null;
    }
}
