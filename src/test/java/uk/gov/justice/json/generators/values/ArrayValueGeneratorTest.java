package uk.gov.justice.json.generators.values;

import static java.util.Arrays.asList;
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

import uk.gov.justice.json.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringPropertyGenerator;

import java.util.List;

public class ArrayValueGeneratorTest {

    @Test
    public void shouldGenerateAJsonArrayValue() throws Exception {

        final String intValue = "23";
        final String stringValue = "\"Fred Bloggs\"";
        final String objectValue = "{\"property\": true}";

        final IntegerPropertyGenerator intGenerator = mock(IntegerPropertyGenerator.class);
        final StringPropertyGenerator stringGenerator = mock(StringPropertyGenerator.class);
        final ObjectPropertyGenerator objectGenerator = mock(ObjectPropertyGenerator.class);

        when(intGenerator.nextJson()).thenReturn(intValue);
        when(stringGenerator.nextJson()).thenReturn(stringValue);
        when(objectGenerator.nextJson()).thenReturn(objectValue);

        final ArrayValueGenerator arrayValueGenerator = new ArrayValueGenerator(asList(intGenerator, stringGenerator, objectGenerator));

        assertThat(arrayValueGenerator.nextValue(), is("[23,\"Fred Bloggs\",{\"property\": true}]"));
    }
}
