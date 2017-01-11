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
        final String propertyName = "integerProperty";
        final NumberSchema.Builder builder = NumberSchema.builder().requiresInteger(true);
        final JsonNumberGenerator jsonNumberGenerator = new JsonNumberGenerator(propertyName,builder.build());
        final JsonNumber jsonString =jsonNumberGenerator.next();
        assertThat(jsonString,isA(JsonNumber.class));
    }
    @Test
    public void shouldGenerateAValidJsonNumberForANumberSchemaProperty(){
        final String propertyName = "integerProperty";
        final NumberSchema.Builder builder = NumberSchema.builder().requiresInteger(true);
        final JsonNumberGenerator jsonNumberGenerator = new JsonNumberGenerator(propertyName,builder.build());
        final JsonNumber jsonString =jsonNumberGenerator.next();
        assertThat(jsonString,isA(JsonNumber.class));
    }
}