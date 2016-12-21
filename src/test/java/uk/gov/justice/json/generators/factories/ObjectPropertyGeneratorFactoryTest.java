package uk.gov.justice.json.generators.factories;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
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

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectJsonPropertyGenerator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class ObjectPropertyGeneratorFactoryTest {

    @Mock
    private FactoryProvider factoryProvider;

    @InjectMocks
    private ObjectPropertyGeneratorFactory objectPropertyGeneratorFactory;

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

        final BasicPropertyGeneratorFactory basicPropertyGeneratorFactory = mock(BasicPropertyGeneratorFactory.class);
        final JsonPropertyGenerator propertyGenerator_1 = mock(JsonPropertyGenerator.class);
        final JsonPropertyGenerator propertyGenerator_2 = mock(JsonPropertyGenerator.class);
        final JsonPropertyGenerator propertyGenerator_3 = mock(JsonPropertyGenerator.class);


        when(factoryProvider.createNewPropertyGeneratorFactory()).thenReturn(basicPropertyGeneratorFactory);

        when(basicPropertyGeneratorFactory.createGenerator(propertyName_1, propertyValue_1)).thenReturn(propertyGenerator_1);
        when(basicPropertyGeneratorFactory.createGenerator(propertyName_2, propertyValue_2)).thenReturn(propertyGenerator_2);
        when(basicPropertyGeneratorFactory.createGenerator(propertyName_3, propertyValue_3)).thenReturn(propertyGenerator_3);

        final ObjectJsonPropertyGenerator objectJsonPropertyGenerator = objectPropertyGeneratorFactory.createGenerator(
                objectPropertyName,
                properties);

        assertThat(objectJsonPropertyGenerator.getName(), is(objectPropertyName));
        assertThat(objectJsonPropertyGenerator.getJsonPropertyGenerators().size(), is(3));
        assertThat(objectJsonPropertyGenerator.getJsonPropertyGenerators(), hasItem(propertyGenerator_1));
        assertThat(objectJsonPropertyGenerator.getJsonPropertyGenerators(), hasItem(propertyGenerator_2));
        assertThat(objectJsonPropertyGenerator.getJsonPropertyGenerators(), hasItem(propertyGenerator_3));
    }
}
