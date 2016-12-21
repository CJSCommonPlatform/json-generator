package uk.gov.justice.json.generators.selectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.BooleanJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimeJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringJsonPropertyGenerator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertyGeneratorSelectorTest {

    @Mock
    private SelectorFactory selectorFactory;

    @Mock
    private ObjectGeneratorSelector objectGeneratorSelector;

    @InjectMocks
    private PropertyGeneratorSelector propertyGeneratorSelector;

    @Test
    public void shouldCreateAStringPropertyGeneratorIfTheTypeIsString() throws Exception {

        final String propertyName = "stringProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(StringJsonPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAnEmailPropertyGeneratorIfTheTypeIsStringAndTheFormatIsEmail() throws Exception {

        final String propertyName = "stringProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");
        propertyDefinitions.put("format", "email");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(EmailJsonPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateADateTimePropertyGeneratorIfTheTypeIsStringAndTheFormatIsDateTime() throws Exception {

        final String propertyName = "dateProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");
        propertyDefinitions.put("format", "date-time");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(IsoDateTimeJsonPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateARegexPropertyGeneratorIfTheTypeIsStringAndThePatternPropertyIsSet() throws Exception {

        final String propertyName = "regexProperty";
        final String pattern = "$a|regex|pattern^";

        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");
        propertyDefinitions.put("pattern", pattern);

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(RegexJsonPropertyGenerator.class)));

        assertThat(generator.getName(), is(propertyName));
        assertThat(((RegexJsonPropertyGenerator) generator).getPattern(), is(pattern));
    }

    @Test
    public void shouldCreateAnIntegerPropertyGeneratorIfTheTypeIsInteger() throws Exception {

        final String propertyName = "integerProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "integer");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(IntegerJsonPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateABooleanPropertyGeneratorIfTheTypeIsBoolean() throws Exception {

        final String propertyName = "booleanProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "boolean");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(BooleanJsonPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAnObjectFactoryIfTheTypeIsObject() throws Exception {

        final String propertyName = "objectProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        final Map<String, Object> properties = new HashMap<>();
        propertyDefinitions.put("type", "object");
        propertyDefinitions.put("properties", properties);

        final ObjectJsonPropertyGenerator objectJsonPropertyGenerator = mock(ObjectJsonPropertyGenerator.class);

        when(selectorFactory.createNewObjectGeneratorSelector()).thenReturn(objectGeneratorSelector);
        when(objectGeneratorSelector.createGenerator(propertyName, properties)).thenReturn(objectJsonPropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(
                propertyName,
                propertyDefinitions), is(sameInstance(objectJsonPropertyGenerator)));
    }

    @Test
    public void shouldThrowAJsonGenerationExceptionIfTheTypeIsUnknown() throws Exception {
        final String propertyName = "unknowProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "something-silly");

        try {
            propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
            fail();
        } catch (JsonGenerationException expected) {
            assertThat(expected.getMessage(), is("Unknown property type 'something-silly'"));
        }
    }
}
