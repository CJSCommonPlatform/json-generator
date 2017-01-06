package uk.gov.justice.json.generation.generators.selectors;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generation.generators.definitions.DefinitionsProvider;
import uk.gov.justice.json.generation.generators.properties.*;
import uk.gov.justice.json.generation.generators.selectors.arrays.ArrayGeneratorSelector;
import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void shouldCreateAReferencePropertyGeneratorIfTheReferenceTypeIsString() throws Exception {
        final String propertyName = "referenceProperty";
        final String definitionName = "uuid";

        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        final StringPropertyGenerator stringPropertyGeneratorExpected =new StringPropertyGenerator(propertyName);

        DefinitionsProvider.addPropertyGenerator(definitionName,stringPropertyGeneratorExpected);

        propertyDefinitions.put("$ref","#/definitions/"+ definitionName);

        JsonPropertyGenerator stringPropertyGeneratorActual = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(stringPropertyGeneratorActual, is(stringPropertyGeneratorExpected));
    }

    @Test
    public void shouldCreateAEnumPropertyGeneratorIfTheReferenceTypeIsEnum() throws Exception {
        final String propertyName = "enumProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        final List<Object> enumsValues = new ArrayList<>();
        enumsValues.add("one");
        enumsValues.add("two");
        enumsValues.add("three");
        enumsValues.add("four");

        propertyDefinitions.put("enum",enumsValues );

        assertThat(propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions), is(instanceOf(EnumPropertyGenerator.class)));
    }


    @Test
    public void shouldCreateAOneOfPropertyGeneratorIfTheReferenceTypeIsOneOf() throws Exception {
        final Map<String, Object> propertyDefinitions =  new HashMap<>();

        final String propertyName = "oneOfProperty";
        final List<Object> objects = new ArrayList<>();
        final RandomListItemSelector randomListItemSelector = mock(RandomListItemSelector.class);
        final Map<String, Object> objectDefinitions = new ImmutableMap.Builder<String, Object>()
                .put("properties", new ImmutableMap.Builder<String, Object>()
                        .put("stringProperty", new ImmutableMap.Builder<String, Object>()
                                .put("type", "string")
                                .build())
                        .build())
                .build();

        final List<Object> properties = new ArrayList<>();
        properties.add("stringProperty");
        properties.add(objectDefinitions);
        propertyDefinitions.put("oneOf",properties );

        when(randomListItemSelector.selectRandomlyFrom(objects)).thenReturn(objectDefinitions);

        final ObjectPropertyGenerator objectPropertyGenerator = (ObjectPropertyGenerator) propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
        assertThat(objectPropertyGenerator, is(instanceOf(ObjectPropertyGenerator.class)));

       assertThat(objectPropertyGenerator.getName(), is(propertyName));
        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().size(), is(1));
       assertThat(objectPropertyGenerator.getJsonPropertyGenerators().get(0), is(instanceOf(StringPropertyGenerator.class)));
        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().get(0).getName(), is("stringProperty"));
    }

    @Test
    public void shouldCreateARegexPropertyGeneratorIfTheReferenceTypeIsString() throws Exception {

        final String propertyName = "regexStringProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");
        propertyDefinitions.put("pattern", "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$");

        final RegexPropertyGenerator regexPropertyGenerator = mock(RegexPropertyGenerator.class);

        when(stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions)).thenReturn(regexPropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions), is(regexPropertyGenerator));
    }

    @Test
    public void shouldCreateAEmailPropertyGeneratorIfTheReferenceTypeIsString() throws Exception {

        final String propertyName = "emailStringProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");
        propertyDefinitions.put("format", "email");

        final EmailPropertyGenerator emailPropertyGenerator = mock(EmailPropertyGenerator.class);

        when(stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions)).thenReturn(emailPropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions), is(emailPropertyGenerator));
    }

    @Test
    public void shouldCreateAIsoDateTimePropertyGeneratorIfTheReferenceTypeIsString() throws Exception {

        final String propertyName = "dateTimeProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "string");
        propertyDefinitions.put("format", "date-time");

        final IsoDateTimePropertyGenerator isoDateTimePropertyGenerator = mock(IsoDateTimePropertyGenerator.class);

        when(stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions)).thenReturn(isoDateTimePropertyGenerator);

        assertThat(propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions), is(isoDateTimePropertyGenerator));
    }


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
