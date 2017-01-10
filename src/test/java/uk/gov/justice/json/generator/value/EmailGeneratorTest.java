package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailGeneratorTest {
    @Mock
    private EmailAddressGenerator emailAddressGenerator;

    @InjectMocks
    private EmailGenerator emailGenerator;

    @Test
    public void shouldGenerateARandomEmailInQuotes() throws Exception {

        final String randomEmail = "fred.bloggs@gerritt.com";
        when(emailAddressGenerator.next()).thenReturn(randomEmail);

        final String jsonEmailValue = emailGenerator.nextValue();

        assertThat(jsonEmailValue, is(randomEmail));
    }
}