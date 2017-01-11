package uk.gov.justice.json.generator;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import javax.json.JsonNumber;
import javax.json.JsonString;

import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.junit.Test;

public class JsonNumberGeneratorTest {

    @Test
    public void shouldGenerateAValidJsonNumberForANumberSchemaProperty(){
        final String propertyName = "integerProperty";
        final NumberSchema schema = new NumberSchema();
        final JsonNumberGenerator jsonNumberGenerator = new JsonNumberGenerator(propertyName,schema);
        final JsonNumber jsonString =jsonNumberGenerator.nextValue();
        assertThat(jsonString,isA(JsonNumber.class));
        //assertThat(jsonString, is (Matchers.contains(propertyName)));
    }
}