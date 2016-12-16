package uk.gov.justice.json.generators.properties;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.formatting.CurlyBracedJsonPropertyFormatter;

import org.junit.Test;

public class ObjectJsonPropertyGeneratorTest {

    private static final String PROPERTY_NAME = "objectProperty";

    @Test
    public void shouldGenerateTheCorrectJsonForAnObjectProperty() throws Exception {


        final String propertySting = "\"property%s\": %d";

        final JsonPropertyGenerator propertyGenerator_1 = mock(JsonPropertyGenerator.class);
        final JsonPropertyGenerator propertyGenerator_2 = mock(JsonPropertyGenerator.class);
        when(propertyGenerator_1.nextJson()).thenReturn(format(propertySting, "One", 1));
        when(propertyGenerator_2.nextJson()).thenReturn(format(propertySting, "Two", 2));

        final ObjectJsonPropertyGenerator objectJsonPropertyGenerator = new ObjectJsonPropertyGenerator(
                PROPERTY_NAME,
                asList(propertyGenerator_1, propertyGenerator_2),
                new CurlyBracedJsonPropertyFormatter()
        );

        final String json = objectJsonPropertyGenerator.nextJson();

        assertThat(json, is("\"objectProperty\": { \"propertyOne\": 1,\"propertyTwo\": 2 }"));
    }

    @Test
    public void shouldNotIncludeACommaForOnlyOneChildProperty() throws Exception {


        final String propertySting = "\"property%s\": %d";

        final JsonPropertyGenerator propertyGenerator_1 = mock(JsonPropertyGenerator.class);
        when(propertyGenerator_1.nextJson()).thenReturn(format(propertySting, "One", 1));

        final ObjectJsonPropertyGenerator objectJsonPropertyGenerator = new ObjectJsonPropertyGenerator(
                PROPERTY_NAME,
                singletonList(propertyGenerator_1),
                new CurlyBracedJsonPropertyFormatter()
        );

        final String json = objectJsonPropertyGenerator.nextJson();

        assertThat(json, is("\"objectProperty\": { \"propertyOne\": 1 }"));
    }
}
