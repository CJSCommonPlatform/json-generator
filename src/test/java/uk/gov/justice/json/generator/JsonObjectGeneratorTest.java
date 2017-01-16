package uk.gov.justice.json.generator;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static uk.gov.justice.json.MatcherBasedTypeCheck.Times.times;
import static uk.gov.justice.json.MatcherBasedTypeCheck.typeCheck;
import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonString;

import uk.gov.justice.json.JsonSchemaLoader;
import uk.gov.justice.json.generator.jsonvalue.EnumJsonValueGenerator;
import uk.gov.justice.services.test.utils.core.helper.TypeCheck;
import uk.gov.justice.services.test.utils.core.random.Generator;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import com.jayway.jsonassert.JsonAssert;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.junit.Test;

public class JsonObjectGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;

    @Test
    public void shouldGenerateFromASimpleSchema() throws IOException, URISyntaxException {
        final Schema schema = JsonSchemaLoader.loadSchema("src/test/resources/schemas/simple.object.json-schema.json");

        final JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator((ObjectSchema) schema);

        final JsonObject jsonObject = jsonObjectGenerator.next();

        JsonAssert.with(jsonObject.toString())
                .assertThat("$." + "streetAddress", isA(String.class))
                .assertThat("$." + "city", isA(String.class));

    }
    @Test
    public void shouldGenerateFromEnumWithMixedTypes() {

        final Schema schema = JsonSchemaLoader.loadSchema("src/test/resources/schemas/enums/mixed.enum.json-schema.json");

        EnumJsonValueGenerator jsonEnumGenerator = new EnumJsonValueGenerator((EnumSchema) schema);

        final JsonNumber jsonNumber1 = JsonValueGenerators.buildJsonNumber(valueOf(1.0662));
        final JsonString string =JsonValueGenerators.buildJsonString("string");
        final JsonNumber jsonNumber2 = JsonValueGenerators.buildJsonNumber(valueOf(0.124));
        final JsonNumber jsonNumber3 = JsonValueGenerators.buildJsonNumber(123);
        typeCheck(jsonEnumGenerator, anyOf(is(jsonNumber1),is(string),is(jsonNumber2),is(jsonNumber3)))
                .verify(times(NUMBER_OF_TIMES));

    }


    @Test
    public void shouldGenerateFromComplexEnumWithMixedTypes() {

        final Schema schema = JsonSchemaLoader.loadSchema("src/test/resources/schemas/simple.object.json-schema.json");

        final JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator((ObjectSchema) schema);

        final JsonString red = buildJsonString("red");
        final JsonString amber = buildJsonString("amber");
        final JsonString green = buildJsonString("green");

//        TypeCheck.typeCheck(jsonObjectGenerator, anyOf(is(red),is(amber),is(green)))
//                .verify(TypeCheck.Times.times(NUMBER_OF_TIMES));
    }

}