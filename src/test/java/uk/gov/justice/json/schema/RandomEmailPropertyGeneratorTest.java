package uk.gov.justice.json.schema;

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
