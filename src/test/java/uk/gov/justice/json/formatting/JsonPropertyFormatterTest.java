package uk.gov.justice.json.formatting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class JsonPropertyFormatterTest {

    private final JsonPropertyFormatter jsonPropertyFormatter = new JsonPropertyFormatter();

    @Test
    public void shouldGenerateTheCorrectJsonFragmentForAQuotedProperty() throws Exception {

        final String name = "myProperty";
        final boolean value = true;

        assertThat(jsonPropertyFormatter.toJson(name, value), is("\"myProperty\": true"));
    }
}
