package uk.gov.justice.json.formatting;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
