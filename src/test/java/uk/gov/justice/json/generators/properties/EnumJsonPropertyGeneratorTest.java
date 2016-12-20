package uk.gov.justice.json.generators.properties;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class EnumJsonPropertyGeneratorTest {

    @Test
    public void shouldFormatAStringPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "stringProperty";
        final List<Object> values = singletonList("value");

        final EnumJsonPropertyGenerator enumJsonPropertyGenerator = new EnumJsonPropertyGenerator(
                propertyName,
                values);

        assertThat(enumJsonPropertyGenerator.nextJson(), is("\"stringProperty\": \"value\""));
    }

    @Test
    public void shouldFormatADoublePropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "doubleProperty";
        final List<Object> values = singletonList(23.23);

        final EnumJsonPropertyGenerator enumJsonPropertyGenerator = new EnumJsonPropertyGenerator(
                propertyName,
                values);

        assertThat(enumJsonPropertyGenerator.nextJson(), is("\"doubleProperty\": 23.23"));
    }

    @Test
    public void shouldFormatABooleanPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "booleanProperty";
        final List<Object> values = singletonList(true);

        final EnumJsonPropertyGenerator enumJsonPropertyGenerator = new EnumJsonPropertyGenerator(
                propertyName,
                values);

        assertThat(enumJsonPropertyGenerator.nextJson(), is("\"booleanProperty\": true"));
    }

    @Test
    public void shouldFormatAnIntegerPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "integerProperty";
        final List<Object> values = singletonList(23);

        final EnumJsonPropertyGenerator enumJsonPropertyGenerator = new EnumJsonPropertyGenerator(
                propertyName,
                values);

        assertThat(enumJsonPropertyGenerator.nextJson(), is("\"integerProperty\": 23"));
    }
}
