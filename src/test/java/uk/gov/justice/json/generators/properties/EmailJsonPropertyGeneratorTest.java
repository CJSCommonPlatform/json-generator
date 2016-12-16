package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

import org.junit.Test;

public class EmailJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "emailProperty";

    private final EmailAddressGenerator randomEmailGenerator = mock(EmailAddressGenerator.class);
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = mock(QuotedJsonPropertyFormatter.class);

    private final EmailJsonPropertyGenerator emailJsonPropertyGenerator = new EmailJsonPropertyGenerator(
            PROPERTY_NAME,
            randomEmailGenerator,
            quotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomEmail = "fred.bloggs@gerritt.com";
        final String json = "some json";

        when(randomEmailGenerator.next()).thenReturn(randomEmail);
        when(quotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomEmail)).thenReturn(json);

        assertThat(emailJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomEmail = "fred.bloggs@gerritt.com";
        when(randomEmailGenerator.next()).thenReturn(randomEmail);

        final EmailJsonPropertyGenerator propertyGenerator = new EmailJsonPropertyGenerator(
                PROPERTY_NAME,
                randomEmailGenerator,
                new QuotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"emailProperty\": \"fred.bloggs@gerritt.com\""));
    }
}
