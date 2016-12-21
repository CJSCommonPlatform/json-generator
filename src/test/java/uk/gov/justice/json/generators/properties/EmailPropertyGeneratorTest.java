package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomEmailGenerator;

import org.junit.Test;

public class EmailPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "emailProperty";

    private final RandomEmailGenerator randomEmailGenerator = mock(RandomEmailGenerator.class);
    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter = mock(SimpleJsonPropertyFormatter.class);

    private final EmailPropertyGenerator emailPropertyGenerator = new EmailPropertyGenerator(
            PROPERTY_NAME,
            randomEmailGenerator,
            simpleJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomEmail = "\"fred.bloggs@gerritt.com\"";
        final String json = "some json";

        when(randomEmailGenerator.nextValue()).thenReturn(randomEmail);
        when(simpleJsonPropertyFormatter.toJson(PROPERTY_NAME, randomEmail)).thenReturn(json);

        assertThat(emailPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomEmail = "\"fred.bloggs@gerritt.com\"";
        when(randomEmailGenerator.nextValue()).thenReturn(randomEmail);

        final EmailPropertyGenerator propertyGenerator = new EmailPropertyGenerator(
                PROPERTY_NAME,
                randomEmailGenerator,
                new SimpleJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"emailProperty\": \"fred.bloggs@gerritt.com\""));
    }
}
