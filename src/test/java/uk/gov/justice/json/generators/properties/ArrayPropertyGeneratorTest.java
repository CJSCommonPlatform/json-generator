package uk.gov.justice.json.generators.properties;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generators.values.IntegerValueGenerator;
import uk.gov.justice.json.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generators.values.ObjectValueGenerator;
import uk.gov.justice.json.generators.values.StringValueGenerator;

import java.util.List;

import org.junit.Test;

public class ArrayPropertyGeneratorTest {

    @Test
    public void shouldGenerateCorrectJsonForAnObjectPropertyUsingTheGivenGenerators() throws Exception {

        final String propertyName = "arrayProperty";
        final String intValue = "23";
        final String stringValue = "\"Fred Bloggs\"";
        final String objectValue = "{\"property\": true}";

        final IntegerValueGenerator intGenerator = mock(IntegerValueGenerator.class);
        final StringValueGenerator stringGenerator = mock(StringValueGenerator.class);
        final ObjectValueGenerator objectGenerator = mock(ObjectValueGenerator.class);

        final List<JsonValueGenerator> jsonValueGenerators = asList(intGenerator, stringGenerator, objectGenerator);

        when(intGenerator.nextValue()).thenReturn(intValue);
        when(stringGenerator.nextValue()).thenReturn(stringValue);
        when(objectGenerator.nextValue()).thenReturn(objectValue);


        final ArrayPropertyGenerator arrayPropertyGenerator = new ArrayPropertyGenerator(propertyName, jsonValueGenerators);

        assertThat(arrayPropertyGenerator.nextJson(), is("\"arrayProperty\": [23,\"Fred Bloggs\",{\"property\": true}]"));
    }
}
