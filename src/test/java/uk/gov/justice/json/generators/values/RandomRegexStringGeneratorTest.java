package uk.gov.justice.json.generators.values;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import org.junit.Test;

public class RandomRegexStringGeneratorTest {

    private final RandomRegexStringGenerator randomRegexStringGenerator = new RandomRegexStringGenerator();

    @Test
    public void shouldGenerateString() throws Exception {

        final String uuidPattern = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

        final String randomString = randomRegexStringGenerator.randomString(uuidPattern);

        final UUID uuid = fromString(randomString);

        assertThat(uuid, is(notNullValue()));
    }
}