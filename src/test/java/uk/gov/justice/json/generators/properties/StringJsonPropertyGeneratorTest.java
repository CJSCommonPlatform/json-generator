package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomStringGenerator;

import org.junit.Test;

public class StringJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "stringProperty";

    private final RandomStringGenerator randomStringGenerator = mock(RandomStringGenerator.class);
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = mock(QuotedJsonPropertyFormatter.class);

    private final StringJsonPropertyGenerator stringJsonPropertyGenerator = new StringJsonPropertyGenerator(
            PROPERTY_NAME,
            randomStringGenerator,
            quotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomString = "a_random_string";
        final String json  = "some json";

        when(randomStringGenerator.randomString()).thenReturn(randomString);
        when(quotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomString)).thenReturn(json);

        assertThat(stringJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomString = "a_random_string";
        when(randomStringGenerator.randomString()).thenReturn(randomString);

        final StringJsonPropertyGenerator propertyGenerator = new StringJsonPropertyGenerator(
                PROPERTY_NAME,
                randomStringGenerator,
                new QuotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"stringProperty\": \"a_random_string\""));
    }
}
