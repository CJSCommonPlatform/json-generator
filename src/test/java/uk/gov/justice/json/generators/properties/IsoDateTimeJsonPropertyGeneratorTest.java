package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomDateTimeGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class IsoDateTimeJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "dateTimeProperty";

    private final RandomDateTimeGenerator randomDateTimeGenerator = mock(RandomDateTimeGenerator.class);
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = mock(QuotedJsonPropertyFormatter.class);

    private final IsoDateTimeJsonPropertyGenerator isoDateTimeJsonPropertyGenerator = new IsoDateTimeJsonPropertyGenerator(
            PROPERTY_NAME,
            randomDateTimeGenerator,
            quotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomDate = "2016-08-03T13:55:02+00:00";
        final String json  = "some json";

        when(randomDateTimeGenerator.randomDateTime()).thenReturn(randomDate);
        when(quotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomDate)).thenReturn(json);

        assertThat(isoDateTimeJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomDate = "2016-08-03T13:55:02+00:00";
        when(randomDateTimeGenerator.randomDateTime()).thenReturn(randomDate);

        final IsoDateTimeJsonPropertyGenerator propertyGenerator = new IsoDateTimeJsonPropertyGenerator(
                PROPERTY_NAME,
                randomDateTimeGenerator,
                new QuotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is( "\"dateTimeProperty\": \"2016-08-03T13:55:02+00:00\""));
    }
}