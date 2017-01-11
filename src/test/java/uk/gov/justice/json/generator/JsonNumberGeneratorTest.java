package uk.gov.justice.json.generator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import javax.json.JsonNumber;
import javax.json.JsonString;

import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.hamcrest.Matchers;
import org.junit.Test;

public class JsonNumberGeneratorTest {

    @Test
    public void shouldGenerateAValidJsonNumberForAIntegerSchemaProperty(){
        final NumberSchema schema = NumberSchema.builder().requiresInteger(true).build();
        final JsonNumberGenerator jsonNumberGenerator = new JsonNumberGenerator(schema);
        final JsonNumber jsonString =jsonNumberGenerator.next();
        assertThat(jsonString,isA(JsonNumber.class));
    }
    @Test
    public void shouldGenerateAValidJsonNumberForANumberSchemaProperty(){
        final NumberSchema schema = NumberSchema.builder().requiresInteger(false).build();
        final JsonNumberGenerator jsonNumberGenerator = new JsonNumberGenerator(schema);
        final JsonNumber jsonString =jsonNumberGenerator.next();
        assertThat(jsonString,isA(JsonNumber.class));
    }
}