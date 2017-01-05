package uk.gov.justice.json.generation.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.RegexValueGenerator;

import org.junit.Test;

public class RegexPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "regexProperty";
    private static final String PATTERN = "$a|regex|pattern^";

    private final RegexValueGenerator regexValueGenerator = mock(RegexValueGenerator.class);
    private final JsonPropertyFormatter jsonPropertyFormatter = mock(JsonPropertyFormatter.class);

    private final RegexPropertyGenerator booleanJsonPropertyGenerator = new RegexPropertyGenerator(
            PROPERTY_NAME,
            PATTERN,
            regexValueGenerator,
            jsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomRegexString = "generated_from_regex_pattern";
        final String json  = "some json";

        when(regexValueGenerator.nextValue()).thenReturn(randomRegexString);
        when(jsonPropertyFormatter.toJson(PROPERTY_NAME, randomRegexString)).thenReturn(json);

        assertThat(booleanJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomRegexString = DOUBLE_QUOTE + "generated_from_regex_pattern" + DOUBLE_QUOTE;
        when(regexValueGenerator.nextValue()).thenReturn(randomRegexString);

        final RegexPropertyGenerator propertyGenerator = new RegexPropertyGenerator(
                PROPERTY_NAME,
                PATTERN,
                regexValueGenerator,
                new JsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"regexProperty\": \"generated_from_regex_pattern\""));
    }
}
