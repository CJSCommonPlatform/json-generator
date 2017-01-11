package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringValueGeneratorTest {

    @Test
    public void shouldGenerateARandomString() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator();
        assertThat(stringValueGenerator.next(), isA(String.class));
    }
}
