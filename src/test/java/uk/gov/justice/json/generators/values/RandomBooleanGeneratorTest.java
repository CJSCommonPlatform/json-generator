package uk.gov.justice.json.generators.values;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomBooleanGeneratorTest {

    @Mock
    private BooleanGenerator booleanGenerator;

    @InjectMocks
    private RandomBooleanGenerator randomBooleanGenerator;

    @Test
    public void shouldReturnARandomBooleanAsAString() throws Exception {

        when(booleanGenerator.next()).thenReturn(true, false, true, true, false);

        assertThat(randomBooleanGenerator.nextValue(), is("true"));
        assertThat(randomBooleanGenerator.nextValue(), is("false"));
        assertThat(randomBooleanGenerator.nextValue(), is("true"));
        assertThat(randomBooleanGenerator.nextValue(), is("true"));
        assertThat(randomBooleanGenerator.nextValue(), is("false"));
    }
}
