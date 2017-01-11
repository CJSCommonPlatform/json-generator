package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringValueGeneratorTest {

    @Test
    public void shouldGenerateARandomStringInQuotes() throws Exception {
        StringValueGenerator stringValueGenerator = new StringValueGenerator();
        assertThat(stringValueGenerator.nextValue(), isA(String.class));
    }
}
