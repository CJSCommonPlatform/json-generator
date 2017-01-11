package uk.gov.justice.json.generator;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import javax.json.JsonString;

import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.junit.Test;

public class JsonStringGeneratorTest {

    @Test
    public void shouldGenerateAValidJsonStringForASimpleStringSchemaProperty() {
        final String propertyName = "stringProperty";
        final StringSchema schema = new StringSchema();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(schema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringWithEmailForEmailSchemaProperty() throws Exception {

        final String propertyName = "emailProperty";
        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new EmailFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }


    @Test
    public void shouldGetAValidJsonStringWithDateTimeForDateTimeSchemaProperty() throws Exception {

        final String propertyName = "dateTimeProperty";
        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new DateTimeFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringWithRegexPatternForRegexSchemaProperty() throws Exception {

        final String propertyName = "regexProperty";
        final String pattern = "$.my|regex[1].^";
        final StringSchema stringSchema = new StringSchema().builder().pattern(pattern).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }
}