package uk.gov.justice.json.generation.generators.properties;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class EnumPropertyGeneratorTest {

    @Test
    public void shouldFormatAStringPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "stringProperty";
        final Set<Object> values = singleton("value");

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"stringProperty\": \"value\""));
    }

    @Test
    public void shouldFormatADoublePropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "doubleProperty";
        final Set<Object> values = singleton(23.23);

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"doubleProperty\": 23.23"));
    }

    @Test
    public void shouldFormatABooleanPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "booleanProperty";
        final Set<Object> values = singleton(true);

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"booleanProperty\": true"));
    }

    @Test
    public void shouldFormatAnIntegerPropertyIntoAJsonFragment() throws Exception {

        final String propertyName = "integerProperty";
        final Set<Object> values = singleton(23);

        final EnumPropertyGenerator enumPropertyGenerator = new EnumPropertyGenerator(
                propertyName,
                values);

        assertThat(enumPropertyGenerator.nextJson(), is("\"integerProperty\": 23"));
    }
}
