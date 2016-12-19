package uk.gov.justice.json.schema;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

import java.util.UUID;

import org.junit.Test;


public class RandomRegexStringJsonPropertyGeneratorTest {


    private static final String UUID_PATTERN = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
    private final RandomRegexStringGenerator randomRegexStringGenerator = new RandomRegexStringGenerator(UUID_PATTERN);

    @Test
    public void shouldGenerateARandomStringThatWouldMatchARegex() throws Exception {

        final String randomString = randomRegexStringGenerator.nextValue();

        assertThat(randomString, startsWith(DOUBLE_QUOTE));
        assertThat(randomString, endsWith(DOUBLE_QUOTE));

        final String uuidString = randomString.substring(1, randomString.length() - 1);
        final UUID uuid = fromString(uuidString);

        assertThat(uuid, is(notNullValue()));
    }
}
