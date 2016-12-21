package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generators.values.IntegerValueGenerator;

import org.junit.Test;

public class IntegerPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "integerProperty";

    private final IntegerValueGenerator integerValueGenerator = mock(IntegerValueGenerator.class);
    private final JsonPropertyFormatter jsonPropertyFormatter = mock(JsonPropertyFormatter.class);

    private final IntegerPropertyGenerator integerPropertyGenerator = new IntegerPropertyGenerator(
            PROPERTY_NAME,
            integerValueGenerator,
            jsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomInt = "23";
        final String json  = "some json";

        when(integerValueGenerator.nextValue()).thenReturn(randomInt);
        when(jsonPropertyFormatter.toJson(PROPERTY_NAME, randomInt)).thenReturn(json);

        assertThat(integerPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomInt = "23";
        when(integerValueGenerator.nextValue()).thenReturn(randomInt);

        final IntegerPropertyGenerator propertyGenerator = new IntegerPropertyGenerator(
                PROPERTY_NAME,
                integerValueGenerator,
                new JsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"integerProperty\": 23"));
    }
}
