package uk.gov.justice.json.generation.generators.selectors;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generation.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.EnumPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

import java.util.ArrayList;
import java.util.List;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.BooleanSchema;
import org.everit.json.schema.CombinedSchema;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.ReferenceSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertyGeneratorSelectorTest {

    private PropertyGeneratorSelector propertyGeneratorSelector = new PropertyGeneratorSelector();

    @Test
    public void shouldCreateAReferencePropertyGeneratorIfTheReferenceTypeIsString() throws Exception {
        final String propertyName = "referenceProperty";
        final String refValue = "#/definitions/uuid";
        final String pattern = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        final StringSchema schema =StringSchema.builder().pattern(pattern).build();
        final ReferenceSchema referenceSchema =  new ReferenceSchema(ReferenceSchema.builder().refValue(refValue));
        referenceSchema.setReferredSchema(schema);
        JsonPropertyGenerator stringPropertyGenerator = propertyGeneratorSelector.createGenerator(propertyName,referenceSchema);
        assertThat(stringPropertyGenerator,is(instanceOf(RegexPropertyGenerator.class)));
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
        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, ObjectSchema.builder().build());
        assertThat(propertyGenerator,is(instanceOf(ObjectPropertyGenerator.class)));
        assertThat(propertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldCreateAOneOfPropertyGeneratorIfTheReferenceTypeIsOneOf() throws Exception {
        final String propertyName = "oneOfProperty";

        final StringSchema.Builder stringSchemaBuilder = new StringSchema.Builder();
        stringSchemaBuilder.title("stringProperty");

        final BooleanSchema.Builder booleanSchemaBuilder = new BooleanSchema.Builder();
        booleanSchemaBuilder.title("booleanProperty");

        final CombinedSchema.Builder combinedSchemaBuilder = CombinedSchema.builder();
        combinedSchemaBuilder.criterion(new CombinedSchema.ValidationCriterion() {
            @Override
            public void validate(int matchingCount, int schemaCount) {

            }
        });

        final List<Schema> subschemas =new ArrayList<>();
        subschemas.add(booleanSchemaBuilder.build());
        subschemas.add(stringSchemaBuilder.build());
        combinedSchemaBuilder.subschemas(subschemas).build();

        final JsonPropertyGenerator propertyGenerator = propertyGeneratorSelector.createGenerator(propertyName, combinedSchemaBuilder.build());

        assertThat(propertyGenerator,either(is(instanceOf(BooleanPropertyGenerator.class))).or(is(instanceOf(StringPropertyGenerator.class))));
        assertThat(propertyGenerator.getName(),either(is("booleanProperty")).or(is("stringProperty")));
    }

    @Test
    public void shouldThrowAJsonGenerationExceptionIfTheTypeIsUnknown() throws Exception {
        final String propertyName = "unknowProperty";
        try {
            propertyGeneratorSelector.createGenerator(propertyName, new UnknownSchema(StringSchema.builder()) );
            fail();
       } catch (JsonGenerationException expected) {
            assertThat(expected.getMessage(), is("Unknown property type 'UnknownSchema'"));
        }
    }

    class UnknownSchema extends Schema{

        public UnknownSchema(StringSchema.Builder builder){
            super(builder);

        }
        @Override
        public void validate(Object o) {

        }
    }

}