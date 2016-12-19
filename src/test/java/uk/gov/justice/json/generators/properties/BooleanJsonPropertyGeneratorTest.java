package uk.gov.justice.json.generators.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BooleanJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "booleanProperty";

    private final BooleanGenerator randomBooleanGenerator = mock(BooleanGenerator.class);
    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter = mock(SimpleJsonPropertyFormatter.class);

    private final BooleanJsonPropertyGenerator booleanJsonPropertyGenerator = new BooleanJsonPropertyGenerator(
            PROPERTY_NAME,
            randomBooleanGenerator,
            simpleJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final boolean randomBoolean = true;
        final String json  = "some json";

        when(randomBooleanGenerator.next()).thenReturn(randomBoolean);
        when(simpleJsonPropertyFormatter.toJson(PROPERTY_NAME, randomBoolean)).thenReturn(json);

        assertThat(booleanJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final boolean randomBoolean = true;
        when(randomBooleanGenerator.next()).thenReturn(randomBoolean);

        final BooleanJsonPropertyGenerator propertyGenerator = new BooleanJsonPropertyGenerator(
                PROPERTY_NAME,
                randomBooleanGenerator,
                new SimpleJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"booleanProperty\": true"));
    }
}
