package uk.gov.justice.json.formatting;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CurlyBracedJsonPropertyFormatterTest {

    private final CurlyBracedJsonPropertyFormatter curlyBracedJsonPropertyFormatter = new CurlyBracedJsonPropertyFormatter();

    @Test
    public void shouldGenerateTheCorrectJsonFragmentForObjectsInCurlyBraces() throws Exception {

        final String name = "myProperty";
        final String myObject = "\"stringProperty\": \"stringValue\"";

        final String jsonFragment = curlyBracedJsonPropertyFormatter.toJson(name, myObject);

        assertThat(jsonFragment, is("\"myProperty\": { \"stringProperty\": \"stringValue\" }"));
    }
}
