package uk.gov.justice.json.generation.generators.properties;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class EnumPropertyGeneratorTest {

    @Test
    public void shouldFormatAStringPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "stringProperty";
        final List<Object> values = singletonList("value");

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"stringProperty\": \"value\""));
    }

    @Test
    public void shouldFormatADoublePropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "doubleProperty";
        final List<Object> values = singletonList(23.23);

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"doubleProperty\": 23.23"));
    }

    @Test
    public void shouldFormatABooleanPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "booleanProperty";
        final List<Object> values = singletonList(true);

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"booleanProperty\": true"));
    }

    @Test
    public void shouldFormatAnIntegerPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "integerProperty";
        final List<Object> values = singletonList(23);

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"integerProperty\": 23"));
    }
}
