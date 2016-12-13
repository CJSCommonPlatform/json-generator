package uk.gov.justice.json.generation.generators.values;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class EmailValueGeneratorTest {

    @Mock
    private EmailAddressGenerator emailAddressGenerator;

    @InjectMocks
    private EmailValueGenerator emailValueGenerator;

    @Test
    public void shouldGenerateARandomEmailInQuotes() throws Exception {

        final String randomEmail = "fred.bloggs@gerritt.com";
        when(emailAddressGenerator.next()).thenReturn(randomEmail);

        final String jsonEmailValue = emailValueGenerator.nextValue();
        assertThat(jsonEmailValue, startsWith(DOUBLE_QUOTE));
        assertThat(jsonEmailValue, endsWith(DOUBLE_QUOTE));

        assertThat(stripQuotesFrom(jsonEmailValue), is(randomEmail));
    }

    public String stripQuotesFrom(String jsonEmailValue) {
        return jsonEmailValue.substring(1, jsonEmailValue.length() - 1);
    }
}
