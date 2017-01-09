package uk.gov.justice.json.generation.generators.selectors;

import com.google.common.collect.ImmutableMap;
import org.everit.json.schema.*;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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

    //@InjectMocks
    private PropertyGeneratorSelector propertyGeneratorSelector = new PropertyGeneratorSelector();

    @Test
    public void shouldCreateAReferencePropertyGeneratorIfTheReferenceTypeIsString() throws Exception {
        final String propertyName = "referenceProperty";
        final String definitionName = "uuid";

        final StringPropertyGenerator stringPropertyGeneratorExpected =new StringPropertyGenerator(propertyName);

//        propertyDefinitions.put("$ref","#/definitions/"+ definitionName);
//
//           JsonPropertyGenerator stringPropertyGeneratorActual = propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
//        assertThat(stringPropertyGeneratorActual, is(stringPropertyGeneratorExpected));
    }

    @Test
    public void shouldCreateAEnumPropertyGeneratorIfTheReferenceTypeIsEnum() throws Exception {
        final List<Object> enumsValues = new ArrayList<>();
        enumsValues.add("one");
        enumsValues.add("two");
        enumsValues.add("three");
        enumsValues.add("four");
        assertThat(propertyGeneratorSelector.createGenerator("enumProperty", EnumSchema.builder().possibleValue(enumsValues).build()), is(instanceOf(EnumPropertyGenerator.class)));
    }


    @Test
    public void shouldCreateAOneOfPropertyGeneratorIfTheReferenceTypeIsOneOf() throws Exception {
        final Map<String, Object> propertyDefinitions = new HashMap<>();

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
        when(randomListItemSelector.selectRandomlyFrom(objects)).thenReturn(objectDefinitions);

        //final StringSchema stringSchema = StringSchema.builder().
//      final ObjectPropertyGenerator objectPropertyGenerator = (ObjectPropertyGenerator) propertyGeneratorSelector.createGenerator(propertyName, CombinedSchema.builder().subschemas(subschemas));
//        assertThat(objectPropertyGenerator, is(instanceOf(ObjectPropertyGenerator.class)));
//
//       assertThat(objectPropertyGenerator.getName(), is(propertyName));
//        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().size(), is(1));
//       assertThat(objectPropertyGenerator.getJsonPropertyGenerators().get(0), is(instanceOf(StringPropertyGenerator.class)));
//        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().get(0).getName(), is("stringProperty"));
//    }
    }

    @Test
    public void shouldCreateARegexPropertyGeneratorIfTheReferenceTypeIsString() throws Exception {

        final String propertyName = "regexStringProperty";
        final String pattern = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, StringSchema.builder().pattern(pattern).build());
        assertThat(propertyGenerator,is(instanceOf(RegexPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

   @Test
    public void shouldCreateAEmailPropertyGeneratorIfTheReferenceTypeIsString() throws Exception {
        final String propertyName = "emailProperty";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, StringSchema.builder().formatValidator(new EmailFormatValidator()).build());
        assertThat(propertyGenerator,is(instanceOf(EmailPropertyGenerator.class)));
       assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAIsoDateTimePropertyGeneratorIfTheReferenceTypeIsString() throws Exception {
        final String propertyName = "dateTimeProperty";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, StringSchema.builder().formatValidator(new DateTimeFormatValidator()).build());
        assertThat(propertyGenerator,is(instanceOf(IsoDateTimePropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }


    @Test
    public void shouldCreateAStringPropertyGeneratorIfTheTypeIsString() throws Exception {
        final String propertyName = "stringProperty";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, StringSchema.builder().build());
        assertThat(propertyGenerator,is(instanceOf(StringPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAnIntegerPropertyGeneratorIfTheTypeIsInteger() throws Exception {
        final String propertyName = "integerProperty";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, NumberSchema.builder().build());
        assertThat(propertyGenerator,is(instanceOf(IntegerPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateABooleanPropertyGeneratorIfTheTypeIsBoolean() throws Exception {

        final String propertyName = "booleanProperty";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, BooleanSchema.builder().build());
        assertThat(propertyGenerator,is(instanceOf(BooleanPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAnArrayPropertyGeneratorIfTheTypeIsArray() throws Exception {

        final String propertyName = "arrayProperty";
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, ArraySchema.builder().build());
        assertThat(propertyGenerator,is(instanceOf(ArrayPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAnObjectGeneratorIfTheTypeIsObject() throws Exception {

        final String propertyName = "objectProperty";
        final Map<String, Object> properties = new HashMap<>();
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, ObjectSchema.builder().build());
        assertThat(propertyGenerator,is(instanceOf(ObjectPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldThrowAJsonGenerationExceptionIfTheTypeIsUnknown() throws Exception {
        final String propertyName = "unknowProperty";
        final Map<String, Object> propertyDefinitions =  new HashMap<>();
        propertyDefinitions.put("type", "something-silly");

//        try {
//            propertyGeneratorSelector.createGenerator(propertyName, propertyDefinitions);
////            fail();
////        } catch (JsonGenerationException expected) {
////            assertThat(expected.getMessage(), is("Unknown property type 'something-silly'"));
////        }
    }
}
