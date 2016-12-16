package uk.gov.justice.json.formatting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class QuotedJsonPropertyFormatterTest {

    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();

    @Test
    public void shouldGenerateTheCorrectJsonFragmentForAQuotedProperty() throws Exception {

        final String name = "myProperty";
        final String value = "myValue";

        assertThat(quotedJsonPropertyFormatter.toJson(name, value), is("\"myProperty\": \"myValue\""));
    }
}
