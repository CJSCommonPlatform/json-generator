package uk.gov.justice.json.generation.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BooleanPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "booleanProperty";

    private final BooleanGenerator randomBooleanGenerator = mock(BooleanGenerator.class);
    private final JsonPropertyFormatter jsonPropertyFormatter = mock(JsonPropertyFormatter.class);

    private final BooleanPropertyGenerator booleanPropertyGenerator = new BooleanPropertyGenerator(
            PROPERTY_NAME,
            randomBooleanGenerator,
            jsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final boolean randomBoolean = true;
        final String json  = "some json";

        when(randomBooleanGenerator.next()).thenReturn(randomBoolean);
        when(jsonPropertyFormatter.toJson(PROPERTY_NAME, randomBoolean)).thenReturn(json);

        assertThat(booleanPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final boolean randomBoolean = true;
        when(randomBooleanGenerator.next()).thenReturn(randomBoolean);

        final BooleanPropertyGenerator propertyGenerator = new BooleanPropertyGenerator(
                PROPERTY_NAME,
                randomBooleanGenerator,
                new JsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"booleanProperty\": true"));
    }
}
