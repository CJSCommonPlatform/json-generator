package uk.gov.justice.json.generation.generators.properties;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.generators.selectors.PropertyGeneratorSelector;

import org.everit.json.schema.BooleanSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.StringSchema;
import org.junit.Test;

public class ObjectPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "objectProperty";

    @Test
    public void shouldGenerateTheCorrectJsonForAnObjectProperty() throws Exception {

        final String stringPropertyName ="propertyOne";
        final String booleanPropertyName ="propertyTwo";

        final ObjectSchema.Builder objectSchema = ObjectSchema.builder();
        objectSchema.title(PROPERTY_NAME);


        final StringSchema.Builder stringSchemaBuilder = StringSchema.builder();
        stringSchemaBuilder.title(stringPropertyName);

        final BooleanSchema.Builder booleanSchemaBuilder = BooleanSchema.builder();
        booleanSchemaBuilder.title(booleanPropertyName);

        objectSchema
                .addPropertySchema(stringPropertyName,stringSchemaBuilder.build())
                .addPropertySchema(booleanPropertyName,booleanSchemaBuilder.build());

        final StringPropertyGenerator propertyGenerator_1 = mock(StringPropertyGenerator.class);
        final BooleanPropertyGenerator propertyGenerator_2 = mock(BooleanPropertyGenerator.class);

        final ObjectPropertyGenerator objectPropertyGenerator = new ObjectPropertyGenerator(
                PROPERTY_NAME,
                objectSchema.build(),new PropertyGeneratorSelector()
        );

        final String json = objectPropertyGenerator.nextJson();

        assertThat(json, is("\"objectProperty\": {\"propertyOne\": 1,\"propertyTwo\": 2}"));
    }

    @Test
    public void shouldNotIncludeACommaForOnlyOneChildProperty() throws Exception {


        final String propertySting = "\"property%s\": %d";

        final JsonPropertyGenerator propertyGenerator_1 = mock(JsonPropertyGenerator.class);
        when(propertyGenerator_1.nextJson()).thenReturn(format(propertySting, "One", 1));
//
//        final ObjectPropertyGenerator objectPropertyGenerator = new ObjectPropertyGenerator(
//                PROPERTY_NAME,
//                singletonList(propertyGenerator_1)
//        );
//
//        final String json = objectPropertyGenerator.nextJson();
//
//        assertThat(json, is("\"objectProperty\": {\"propertyOne\": 1}"));
    }
}
