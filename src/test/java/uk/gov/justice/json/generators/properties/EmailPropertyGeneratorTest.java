package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generators.values.EmailValueGenerator;

import org.junit.Test;

public class EmailPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "emailProperty";

    private final EmailValueGenerator emailValueGenerator = mock(EmailValueGenerator.class);
    private final JsonPropertyFormatter jsonPropertyFormatter = mock(JsonPropertyFormatter.class);

    private final EmailPropertyGenerator emailPropertyGenerator = new EmailPropertyGenerator(
            PROPERTY_NAME,
            emailValueGenerator,
            jsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomEmail = "\"fred.bloggs@gerritt.com\"";
        final String json = "some json";

        when(emailValueGenerator.nextValue()).thenReturn(randomEmail);
        when(jsonPropertyFormatter.toJson(PROPERTY_NAME, randomEmail)).thenReturn(json);

        assertThat(emailPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomEmail = "\"fred.bloggs@gerritt.com\"";
        when(emailValueGenerator.nextValue()).thenReturn(randomEmail);

        final EmailPropertyGenerator propertyGenerator = new EmailPropertyGenerator(
                PROPERTY_NAME,
                emailValueGenerator,
                new JsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"emailProperty\": \"fred.bloggs@gerritt.com\""));
    }
}
