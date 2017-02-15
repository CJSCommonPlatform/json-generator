package uk.gov.justice.json.generator;

import static org.everit.json.schema.StringSchema.builder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.Times.times;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.typeCheck;

import java.util.Optional;

import javax.json.JsonString;
import javax.json.JsonValue;

import org.apache.commons.validator.routines.RegexValidator;
import org.everit.json.schema.FormatValidator;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.everit.json.schema.internal.HostnameFormatValidator;
import org.everit.json.schema.internal.IPV4Validator;
import org.everit.json.schema.internal.IPV6Validator;
import org.everit.json.schema.internal.URIFormatValidator;
import org.junit.Test;

public class JsonStringGeneratorTest {

    private static final int NUMBER_OF_TIMES =1000;

    @Test
    public void shouldGenerateAValidJsonStringForASimpleStringSchemaProperty() {

        final StringSchema stringSchema = builder().build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        final JsonString jsonString = jsonStringGenerator.next();

        assertThat(jsonString, isA(JsonString.class));
    }

    @Test
    public void shouldGenerateAValidJsonStringForASimpleStringSchemaWithMinAndMaxProperty() {

        final StringSchema stringSchema = builder().minLength(6).maxLength(10).build();

        typeCheck(new JsonStringGenerator(stringSchema), jsonValue->  ((JsonString)jsonValue).getString().length() >=6
                && ((JsonString)jsonValue).getString().length() <10 )
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateAValidJsonStringForASimpleStringSchemaContainingSameMinAndNoMaxProperty() {

        final StringSchema stringSchema = builder().minLength(6).maxLength(6).build();

        typeCheck(new JsonStringGenerator(stringSchema), jsonValue->  ((JsonString)jsonValue).getString().length() ==6)
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateAValidJsonStringForASimpleStringSchemaWithNoMinAndContainingMaxProperty() {

        final StringSchema stringSchema = builder().maxLength(7).build();

        typeCheck(new JsonStringGenerator(stringSchema), jsonValue->  ((JsonString)jsonValue).getString().length() <=7)
             .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGetAValidJsonStringWithEmailForEmailSchemaProperty() throws Exception {

        final StringSchema stringSchema = builder().formatValidator(new EmailFormatValidator()).build();

        typeCheck(new JsonStringGenerator(stringSchema), jsonValue -> validate(new EmailFormatValidator(),jsonValue).equals(Optional.empty()))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGetAValidJsonStringWithDateTimeForDateTimeSchemaProperty() throws Exception {

        final StringSchema stringSchema = builder().formatValidator(new DateTimeFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);

        typeCheck(jsonStringGenerator, jsonString -> new DateTimeFormatValidator().validate(((JsonString)jsonString).getString())
                .equals(Optional.empty()))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGetAValidJsonStringWithRegexPatternForRegexSchemaProperty() throws Exception {

        final String pattern = "$.my|regex[1].^";
        final StringSchema stringSchema = builder().pattern(pattern).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);
        typeCheck(jsonStringGenerator, jsonString -> new RegexValidator(pattern).validate(jsonStringGenerator.toString())==null)
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGetAValidJsonStringWithForHostnameSchemaProperty() throws Exception {

        final StringSchema stringSchema = builder().formatValidator(new HostnameFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);

        typeCheck(jsonStringGenerator, jsonString -> new HostnameFormatValidator().validate(((JsonString)jsonString).getString())
                .equals(Optional.empty()))
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGetAValidJsonStringForURISchemaProperty() throws Exception {

        final StringSchema stringSchema = builder().formatValidator(new URIFormatValidator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);

        typeCheck(jsonStringGenerator, jsonString -> new URIFormatValidator().validate(((JsonString)jsonString).getString())
                .equals(Optional.empty()))
                .verify(times(NUMBER_OF_TIMES));;
    }

    @Test
    public void shouldGetAValidJsonStringForIpv4SchemaProperty() throws Exception {

        final StringSchema stringSchema = builder().formatValidator(new IPV4Validator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);

        typeCheck(jsonStringGenerator, jsonString -> new IPV4Validator().validate(((JsonString)jsonString).getString())
                .equals(Optional.empty()))
                .verify(times(NUMBER_OF_TIMES));;
    }

    @Test
    public void shouldGetAValidJsonStringForIpv6SchemaProperty() throws Exception {

        final StringSchema stringSchema = builder().formatValidator(new IPV6Validator()).build();
        final JsonStringGenerator jsonStringGenerator = new JsonStringGenerator(stringSchema);

        typeCheck(jsonStringGenerator, jsonString -> new IPV6Validator().validate(((JsonString)jsonString).getString())
                .equals(Optional.empty()))
                .verify(times(NUMBER_OF_TIMES));;
    }

    private Optional<String> validate(final FormatValidator validator, final JsonValue jsonValue){
        final String valueString = ((JsonString)jsonValue).getString();
        return validator.validate(valueString);
    }

}
