package uk.gov.justice.json.generation.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import org.junit.Test;

public class StringPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "stringProperty";

    private final StringGenerator randomStringGenerator = mock(StringGenerator.class);
    private final JsonPropertyFormatter quotedJsonPropertyFormatter = mock(JsonPropertyFormatter.class);

    private final StringPropertyGenerator stringPropertyGenerator = new StringPropertyGenerator(
            PROPERTY_NAME,
            randomStringGenerator,
            quotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomString = "\"a_random_string\"";
        final String json  = "some json";

        when(randomStringGenerator.next()).thenReturn(randomString);
        when(quotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomString)).thenReturn(json);

        assertThat(stringPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomString = "\"a_random_string\"";
        when(randomStringGenerator.next()).thenReturn(randomString);

        final StringPropertyGenerator propertyGenerator = new StringPropertyGenerator(
                PROPERTY_NAME,
                randomStringGenerator,
                new JsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"stringProperty\": \"a_random_string\""));
    }
}
