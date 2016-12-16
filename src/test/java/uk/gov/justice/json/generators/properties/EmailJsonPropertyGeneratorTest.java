package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomEmailGenerator;

import org.junit.Test;

public class EmailJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "emailProperty";

    private final RandomEmailGenerator randomEmailGenerator = mock(RandomEmailGenerator.class);
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = mock(QuotedJsonPropertyFormatter.class);

    private final EmailJsonPropertyGenerator emailJsonPropertyGenerator = new EmailJsonPropertyGenerator(
            PROPERTY_NAME,
            randomEmailGenerator,
            quotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomEmail = "fred.bloggs@gerritt.com";
        final String json  = "some json";

        when(randomEmailGenerator.randomEmail()).thenReturn(randomEmail);
        when(quotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomEmail)).thenReturn(json);

        assertThat(emailJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomEmail = "fred.bloggs@gerritt.com";
        when(randomEmailGenerator.randomEmail()).thenReturn(randomEmail);

        final EmailJsonPropertyGenerator propertyGenerator = new EmailJsonPropertyGenerator(
                PROPERTY_NAME,
                randomEmailGenerator,
                new QuotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"emailProperty\": \"fred.bloggs@gerritt.com\""));
    }
}
