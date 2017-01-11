package uk.gov.justice.json.generator.multivalue;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.ArrayValueGenerator;

import org.junit.Test;

public class ArrayGeneratorTest {

    @Test
    public void shouldGenerateAJsonArrayValue() throws Exception {

        final String intValue = "23";
        final String stringValue = "\"Fred Bloggs\"";
        final String objectValue = "{\"property\": true}";
//
//        final JsonArrayGenerator arrayValueGenerator = new JsonArrayGenerator(asList(intValue, stringGenerator, objectGenerator));
//
//        assertThat(arrayValueGenerator.nextValue(), is("[23,\"Fred Bloggs\",{\"property\": true}]"));
    }
}