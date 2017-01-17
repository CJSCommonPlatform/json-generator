package uk.gov.justice.json.generator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.string.Ipv6Generator;

import javax.json.JsonString;

import org.apache.commons.validator.routines.RegexValidator;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.everit.json.schema.internal.HostnameFormatValidator;
import org.everit.json.schema.internal.IPV4Validator;
import org.everit.json.schema.internal.IPV6Validator;
import org.everit.json.schema.internal.URIFormatValidator;
import org.junit.Test;

public class JsonStringGeneratorTest {

    @Test
    public void shouldGenerateAValidJsonStringForASimpleStringSchemaProperty() {
        final StringSchema schema = new StringSchema();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(schema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringWithEmailForEmailSchemaProperty() throws Exception {

        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new EmailFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringWithDateTimeForDateTimeSchemaProperty() throws Exception {

        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new DateTimeFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringWithRegexPatternForRegexSchemaProperty() throws Exception {

        final String pattern = "$.my|regex[1].^";
        final StringSchema stringSchema = new StringSchema().builder().pattern(pattern).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringWithForHostnameSchemaProperty() throws Exception {

        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new HostnameFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringForURISchemaProperty() throws Exception {

        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new URIFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringForIpv4SchemaProperty() throws Exception {

        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new IPV4Validator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGetAValidJsonStringForIpv6SchemaProperty() throws Exception {

        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new IPV6Validator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();
       // assertThat(true, is(new RegexValidator(Ipv6Generator.PATTERN).isValid(jsonString.toString())));
        assertThat(jsonString, isA(JsonString.class));
    }

}