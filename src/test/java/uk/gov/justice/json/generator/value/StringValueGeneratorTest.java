package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.JsonGenerationException;
import uk.gov.justice.json.generator.value.string.SimpleStringGenerator;

import org.junit.Test;

public class StringValueGeneratorTest {

    @Test
    public void shouldGenerateARandomString() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(4,12);
        final String value = stringValueGenerator.next();
        assertThat(value, isA(String.class));
    }

    @Test (expected = JsonGenerationException.class)
    public void shouldGenerateARandomStringWithMinimumGreaterThanMaxLength() throws Exception {
        new SimpleStringGenerator(12,4);
    }

    @Test
    public void shouldGenerateARandomStringWithMinimumAndMaxLengthAsZeroLength() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(0,0);
        final String value = stringValueGenerator.next();
        System.out.println(value);
        assertThat(value, isA(String.class));
        assertThat(value.length(),equalTo(0));
    }

    @Test
    public void shouldGenerateARandomStringWithMinimumZeroAndMaxLengthAsOneLength() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(0,1);
        final String value = stringValueGenerator.next();
        System.out.println(value);
        assertThat(value, isA(String.class));
        assertThat(value.length(),lessThanOrEqualTo(1));
    }

    @Test
    public void shouldGenerateARandomStringWithMinimumLength() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(4,12);
        final String value = stringValueGenerator.next();
        System.out.println(value);
        assertThat(value, isA(String.class));
        assertThat(value.length(),greaterThanOrEqualTo(4));
    }

    @Test
    public void shouldGenerateARandomStringWithMaximumLength() throws Exception {
        SimpleStringGenerator stringValueGenerator = new SimpleStringGenerator(0,12);
        final String value = stringValueGenerator.next();
        System.out.println(value);
        assertThat(value, isA(String.class));
        assertThat(value.length(),lessThanOrEqualTo(12));
    }
}
