package uk.gov.justice.json.generator.multivalue;

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