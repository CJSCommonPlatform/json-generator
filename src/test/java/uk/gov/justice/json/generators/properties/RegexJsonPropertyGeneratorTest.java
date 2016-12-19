package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

import org.junit.Test;

public class RegexJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "regexProperty";
    private static final String PATTERN = "$a|regex|pattern^";

    private final RandomRegexStringGenerator randomRegexStringGenerator = mock(RandomRegexStringGenerator.class);
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = mock(UnquotedJsonPropertyFormatter.class);

    private final RegexJsonPropertyGenerator booleanJsonPropertyGenerator = new RegexJsonPropertyGenerator(
            PROPERTY_NAME,
            PATTERN,
            randomRegexStringGenerator,
            unquotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomRegexString = "generated_from_regex_pattern";
        final String json  = "some json";

        when(randomRegexStringGenerator.nextValue()).thenReturn(randomRegexString);
        when(unquotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomRegexString)).thenReturn(json);

        assertThat(booleanJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomRegexString = DOUBLE_QUOTE + "generated_from_regex_pattern" + DOUBLE_QUOTE;
        when(randomRegexStringGenerator.nextValue()).thenReturn(randomRegexString);

        final RegexJsonPropertyGenerator propertyGenerator = new RegexJsonPropertyGenerator(
                PROPERTY_NAME,
                PATTERN,
                randomRegexStringGenerator,
                new UnquotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"regexProperty\": \"generated_from_regex_pattern\""));
    }
}
