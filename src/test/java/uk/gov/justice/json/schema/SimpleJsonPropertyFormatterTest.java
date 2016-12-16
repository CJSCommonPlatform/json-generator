package uk.gov.justice.json.schema;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;

import org.junit.Test;

public class SimpleJsonPropertyFormatterTest {


    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();

    @Test
    public void shouldCreateCorrectJsonFragment() throws Exception {

        final String jsonFragment = quotedJsonPropertyFormatter.toJson(
                "propertyName",
                "propertyValue");

        assertThat(jsonFragment, is("\"propertyName\": \"propertyValue\""));
    }
}
