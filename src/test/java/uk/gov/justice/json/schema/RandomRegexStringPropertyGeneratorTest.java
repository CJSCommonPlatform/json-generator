package uk.gov.justice.json.schema;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.notNullValue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

import java.util.UUID;


public class RandomRegexStringPropertyGeneratorTest {

    private final RandomRegexStringGenerator randomRegexStringGenerator = new RandomRegexStringGenerator();

    @Test
    public void shouldGenerateARandomStringThatWouldMatchARegex() throws Exception {

        final String uuidPattern = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

        final String randomString = randomRegexStringGenerator.randomString(uuidPattern);

        final UUID uuid = fromString(randomString);

        assertThat(uuid, is(notNullValue()));
    }
}
