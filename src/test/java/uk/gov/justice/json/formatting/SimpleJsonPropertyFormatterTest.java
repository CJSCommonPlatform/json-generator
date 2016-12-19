package uk.gov.justice.json.formatting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class SimpleJsonPropertyFormatterTest {

    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter = new SimpleJsonPropertyFormatter();

    @Test
    public void shouldGenerateTheCorrectJsonFragmentForAQuotedProperty() throws Exception {

        final String name = "myProperty";
        final boolean value = true;

        assertThat(simpleJsonPropertyFormatter.toJson(name, value), is("\"myProperty\": true"));
    }
}
