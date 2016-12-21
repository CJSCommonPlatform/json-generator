package uk.gov.justice.json.generators.values;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class StringValueGeneratorTest {

    @Mock
    private StringGenerator stringGenerator;

    @InjectMocks
    private StringValueGenerator stringValueGenerator;

    @Test
    public void shouldGenerateARandomStringInQuotes() throws Exception {

        final String value = "a random String";

        when(stringGenerator.next()).thenReturn(value);

        assertThat(stringValueGenerator.nextValue(), is("\"a random String\""));
    }
}
