package uk.gov.justice.json.generator.value;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.string.RegexGenerator;

import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexGeneratorTest {

    private static final String UUID_PATTERN = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    private final RegexGenerator regexGenerator = new RegexGenerator(Pattern.compile(UUID_PATTERN));

    // TODO: This test needs to assert that the generated strings match the regex. This is NOT a valid test.
    @Test
    public void shouldGenerateARandomStringFromARegularExpression() throws Exception {

        final String randomString = regexGenerator.next();

        final UUID uuid = fromString(randomString);

        assertThat(uuid, is(notNullValue()));

        assertThat(regexGenerator.next(), is(not(equalTo(randomString))));
    }

}
