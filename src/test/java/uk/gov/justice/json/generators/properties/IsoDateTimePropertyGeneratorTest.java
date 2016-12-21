package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generators.values.DateTimeValueGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class IsoDateTimePropertyGeneratorTest {

    private static final String PROPERTY_NAME = "dateTimeProperty";

    private final DateTimeValueGenerator dateTimeValueGenerator = mock(DateTimeValueGenerator.class);
    private final JsonPropertyFormatter jsonPropertyFormatter = mock(JsonPropertyFormatter.class);

    private final IsoDateTimePropertyGenerator isoDateTimePropertyGenerator = new IsoDateTimePropertyGenerator(
            PROPERTY_NAME,
            dateTimeValueGenerator,
            jsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final String randomDate = "\"2016-08-03T13:55:02+00:00\"";
        final String json  = "some json";

        when(dateTimeValueGenerator.nextValue()).thenReturn(randomDate);
        when(jsonPropertyFormatter.toJson(PROPERTY_NAME, randomDate)).thenReturn(json);

        assertThat(isoDateTimePropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final String randomDate = "\"2016-08-03T13:55:02+00:00\"";
        when(dateTimeValueGenerator.nextValue()).thenReturn(randomDate);

        final IsoDateTimePropertyGenerator propertyGenerator = new IsoDateTimePropertyGenerator(
                PROPERTY_NAME,
                dateTimeValueGenerator,
                new JsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is( "\"dateTimeProperty\": \"2016-08-03T13:55:02+00:00\""));
    }
}
