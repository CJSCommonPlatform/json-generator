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


public class QuotedJsonPropertyFormatterTest {

    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();

    @Test
    public void shouldGenerateTheCorrectJsonFragmentForAQuotedProperty() throws Exception {

        final String name = "myProperty";
        final String value = "myValue";

        assertThat(quotedJsonPropertyFormatter.toJson(name, value), is("\"myProperty\": \"myValue\""));
    }
}
