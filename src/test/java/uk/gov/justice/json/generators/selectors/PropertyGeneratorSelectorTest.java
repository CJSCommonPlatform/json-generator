package uk.gov.justice.json.generators.selectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringPropertyGenerator;
import uk.gov.justice.json.generators.selectors.arrays.ArrayGeneratorSelector;

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

    @Mock
    private StringPropertyGeneratorSelector stringPropertyGeneratorSelector;

    @Mock
    private ArrayGeneratorSelector arrayGeneratorSelector;

    @InjectMocks
    private PropertyGeneratorSelector propertyGeneratorSelector;

    @Test
    public void shouldCreateAStringPropertyGeneratorIfTheTypeIsString() throws Exception {

        final String propertyName = "stringProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");

        final StringPropertyGenerator stringPropertyGenerator = mock(StringPropertyGenerator.class);

        when(stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions)).thenReturn(stringPropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions), is(stringPropertyGenerator));
    }

    @Test
    public void shouldCreateAnIntegerPropertyGeneratorIfTheTypeIsInteger() throws Exception {

        final String propertyName = "integerProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "integer");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(IntegerPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateABooleanPropertyGeneratorIfTheTypeIsBoolean() throws Exception {

        final String propertyName = "booleanProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "boolean");

        final JsonPropertyGenerator generator = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(generator, is(instanceOf(BooleanPropertyGenerator.class)));
        assertThat(generator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAnArrayPropertyGeneratorIfTheTypeIsArray() throws Exception {

        final String propertyName = "arrayProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "array");

        final ArrayPropertyGenerator arrayPropertyGenerator = mock(ArrayPropertyGenerator.class);

        when(arrayGeneratorSelector.getArrayGenerator(propertyName, propertyDefinitions)).thenReturn(arrayPropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions), is(arrayPropertyGenerator));
    }

    @Test
    public void shouldCreateAnObjectGeneratorIfTheTypeIsObject() throws Exception {

        final String propertyName = "objectProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        final Map<String, Object> properties = new HashMap<>();
        propertyDefinitions.put("type", "object");
        propertyDefinitions.put("properties", properties);

        final ObjectPropertyGenerator objectPropertyGenerator = mock(ObjectPropertyGenerator.class);

        when(selectorFactory.createNewObjectGeneratorSelector()).thenReturn(objectGeneratorSelector);
        when(objectGeneratorSelector.createGenerator(propertyName, properties)).thenReturn(objectPropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(
                propertyName,
                propertyDefinitions), is(sameInstance(objectPropertyGenerator)));
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
