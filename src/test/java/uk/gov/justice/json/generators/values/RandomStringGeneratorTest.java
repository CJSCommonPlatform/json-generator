package uk.gov.justice.json.generators.values;

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

import uk.gov.justice.services.test.utils.core.random.StringGenerator;


@RunWith(MockitoJUnitRunner.class)
public class RandomStringGeneratorTest {

    @Mock
    private StringGenerator stringGenerator;

    @InjectMocks
    private RandomStringGenerator randomStringGenerator;

    @Test
    public void shouldGenerateARandomStringInQuotes() throws Exception {

        final String value = "a random String";

        when(stringGenerator.next()).thenReturn(value);

        assertThat(randomStringGenerator.nextValue(), is("\"a random String\""));
    }
}
