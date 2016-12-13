package uk.gov.justice.json.generation.generators.values;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexValueGeneratorTest {


    private static final String UUID_PATTERN = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

    private final RegexValueGenerator regexValueGenerator = new RegexValueGenerator(Pattern.compile(UUID_PATTERN));

    //@Test
    public void shouldGenerateAStringFromARegularExpression() throws Exception {

        final String randomString = regexValueGenerator.nextValue();

        assertThat(randomString, startsWith(DOUBLE_QUOTE));
        assertThat(randomString, endsWith(DOUBLE_QUOTE));

        final UUID uuid = fromString(stripQuotesFrom(randomString));

        assertThat(uuid, is(notNullValue()));

        assertThat(regexValueGenerator.nextValue(), is(not(equalTo(randomString))));
    }

    private String stripQuotesFrom(final String randomString) {
        return randomString.substring(2, randomString.length() - 1);
    }
}
