package uk.gov.justice.json.formatting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class UnquotedJsonPropertyFormatterTest {

    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = new UnquotedJsonPropertyFormatter();

    @Test
    public void shouldGenerateTheCorrectJsonFragmentForAQuotedProperty() throws Exception {

        final String name = "myProperty";
        final boolean value = true;

        assertThat(unquotedJsonPropertyFormatter.toJson(name, value), is("\"myProperty\": true"));
    }
}
