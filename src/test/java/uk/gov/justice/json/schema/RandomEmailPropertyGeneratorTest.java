package uk.gov.justice.json.schema;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generators.values.RandomEmailGenerator;

public class RandomEmailPropertyGeneratorTest {

    private final RandomEmailGenerator randomEmailGenerator = new RandomEmailGenerator();

    @Test
    public void shouldGenerateARandomEmailAddress() throws Exception {

        final int count = 1000;

        for (int i = 0; i < count; i++) {
            final String randomEmail = randomEmailGenerator.randomEmail();

            final String[] split = randomEmail.split("@");

            assertThat(split.length, is(2));
            assertThat(split[0].length(), is(10));
            assertThat(split[1], is("test.com"));
        }
    }
}
