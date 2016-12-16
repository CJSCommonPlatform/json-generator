package uk.gov.justice.json.generators.properties;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomBooleanGenerator;


@RunWith(MockitoJUnitRunner.class)
public class BooleanJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "booleanProperty";

    private final RandomBooleanGenerator randomBooleanGenerator = mock(RandomBooleanGenerator.class);
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = mock(UnquotedJsonPropertyFormatter.class);

    private final BooleanJsonPropertyGenerator booleanJsonPropertyGenerator = new BooleanJsonPropertyGenerator(
            PROPERTY_NAME,
            randomBooleanGenerator,
            unquotedJsonPropertyFormatter
    );

    @Test
    public void shouldGenerateCorrectJsonForABooleanPropertyWithARandomValue() throws Exception {

        final boolean randomBoolean = true;
        final String json  = "some json";

        when(randomBooleanGenerator.randomBoolean()).thenReturn(randomBoolean);
        when(unquotedJsonPropertyFormatter.toJson(PROPERTY_NAME, randomBoolean)).thenReturn(json);

        assertThat(booleanJsonPropertyGenerator.nextJson(), is(json));
    }

    @Test
    public void shouldGenerateValidJson() throws Exception {

        final boolean randomBoolean = true;
        when(randomBooleanGenerator.randomBoolean()).thenReturn(randomBoolean);

        final BooleanJsonPropertyGenerator propertyGenerator = new BooleanJsonPropertyGenerator(
                PROPERTY_NAME,
                randomBooleanGenerator,
                new UnquotedJsonPropertyFormatter()
        );

        final String json = propertyGenerator.nextJson();

        assertThat(json, is("\"booleanProperty\": true"));
    }
}
