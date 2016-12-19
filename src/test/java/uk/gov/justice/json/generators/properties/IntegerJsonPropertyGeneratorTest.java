package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomIntegerGenerator;

import org.junit.Test;

public class IntegerJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "integerProperty";

    private final RandomIntegerGenerator randomIntegerGenerator = mock(RandomIntegerGenerator.class);
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = mock(UnquotedJsonPropertyFormatter.class);

    private final IntegerJsonPropertyGenerator integerJsonPropertyGenerator = new IntegerJsonPropertyGenerator(
            PROPERTY_NAME,
            randomIntegerGenerator,
            unquotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomInt = "23";
        final String json  = "some json";

        when(randomIntegerGenerator.nextValue()).thenReturn(randomInt);
        when(unquotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomInt)).thenReturn(json);

        assertThat(integerJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomInt = "23";
        when(randomIntegerGenerator.nextValue()).thenReturn(randomInt);

        final IntegerJsonPropertyGenerator propertyGenerator = new IntegerJsonPropertyGenerator(
                PROPERTY_NAME,
                randomIntegerGenerator,
                new UnquotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"integerProperty\": 23"));
    }
}
