package uk.gov.justice.json.generation.generators.values;

import static java.util.Arrays.asList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import uk.gov.justice.json.generation.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

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
