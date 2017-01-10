package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringValueGeneratorTest {

    @Mock
    private uk.gov.justice.services.test.utils.core.random.StringGenerator stringGenerator;

    @InjectMocks
    private uk.gov.justice.json.generation.generators.values.StringValueGenerator stringValueGenerator;

    @Test
    public void shouldGenerateARandomStringInQuotes() throws Exception {

        final String value = "a random String";

        when(stringGenerator.next()).thenReturn(value);

        assertThat(stringValueGenerator.nextValue(), is("\"a random String\""));
    }
}
