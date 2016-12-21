package uk.gov.justice.json.generators.selectors;

import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectPropertyGenerator;

import java.util.HashMap;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class ObjectGeneratorSelectorTest {

    @Mock
    private SelectorFactory selectorFactory;

    @InjectMocks
    private ObjectGeneratorSelector objectGeneratorSelector;

    @Test
    public void shouldCreateAnObjectPropertyGeneratorWithCorrectJsonPropertyGenerators() throws Exception {

        final String objectPropertyName = "objectProperty";

        final String propertyName_1 = "propertyName_1";
        final String propertyName_2 = "propertyName_2";
        final String propertyName_3 = "propertyName_3";
        final Object propertyValue_1 = new Object();
        final Object propertyValue_2 = new Object();
        final Object propertyValue_3 = new Object();

        final Map<String, Object> properties = new HashMap<>();

        properties.put(propertyName_1, propertyValue_1);
        properties.put(propertyName_2, propertyValue_2);
        properties.put(propertyName_3, propertyValue_3);

        final PropertyGeneratorSelector propertyGeneratorSelector = mock(PropertyGeneratorSelector.class);
        final JsonPropertyGenerator propertyGenerator_1 = mock(JsonPropertyGenerator.class);
        final JsonPropertyGenerator propertyGenerator_2 = mock(JsonPropertyGenerator.class);
        final JsonPropertyGenerator propertyGenerator_3 = mock(JsonPropertyGenerator.class);


        when(selectorFactory.createNewPropertyGeneratorSelector()).thenReturn(propertyGeneratorSelector);

        when(propertyGeneratorSelector.createGenerator(propertyName_1, propertyValue_1)).thenReturn(propertyGenerator_1);
        when(propertyGeneratorSelector.createGenerator(propertyName_2, propertyValue_2)).thenReturn(propertyGenerator_2);
        when(propertyGeneratorSelector.createGenerator(propertyName_3, propertyValue_3)).thenReturn(propertyGenerator_3);

        final ObjectPropertyGenerator objectPropertyGenerator = objectGeneratorSelector.createGenerator(
                objectPropertyName,
                properties);

        assertThat(objectPropertyGenerator.getName(), is(objectPropertyName));
        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().size(), is(3));
        assertThat(objectPropertyGenerator.getJsonPropertyGenerators(), hasItem(propertyGenerator_1));
        assertThat(objectPropertyGenerator.getJsonPropertyGenerators(), hasItem(propertyGenerator_2));
        assertThat(objectPropertyGenerator.getJsonPropertyGenerators(), hasItem(propertyGenerator_3));
    }
}
