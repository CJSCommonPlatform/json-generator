package uk.gov.justice.json.generators.values;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import java.util.UUID;

import org.junit.Test;

public class RandomRegexStringGeneratorTest {


    private static final String UUID_PATTERN = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

    private final RandomRegexStringGenerator randomRegexStringGenerator = new RandomRegexStringGenerator(UUID_PATTERN);

    @Test
    public void shouldGenerateString() throws Exception {

        final String randomString = randomRegexStringGenerator.nextValue();

        assertThat(randomString, startsWith(DOUBLE_QUOTE));
        assertThat(randomString, endsWith(DOUBLE_QUOTE));

        final UUID uuid = fromString(stripQuotesFrom(randomString));

        assertThat(uuid, is(notNullValue()));
    }

    private String stripQuotesFrom(final String randomString) {
        return randomString.substring(2, randomString.length() - 1);
    }
}
