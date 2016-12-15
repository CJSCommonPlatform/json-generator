package uk.gov.justice.json.schema;

import static java.util.UUID.fromString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
