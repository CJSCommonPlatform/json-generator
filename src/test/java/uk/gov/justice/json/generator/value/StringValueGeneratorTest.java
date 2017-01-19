package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.string.SimpleStringGenerator;

import org.junit.Test;

public class StringValueGeneratorTest {

    @Test
    public void shouldGenerateARandomString() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(4,12);
        final String value = stringValueGenerator.next();
        assertThat(value, isA(String.class));
    }

    @Test
    public void shouldGenerateARandomStringWithMinimumLength() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(4,12);
        final String value = stringValueGenerator.next();
        assertThat(value, isA(String.class));
        assertThat(value.length(),greaterThanOrEqualTo(4));
    }
}
