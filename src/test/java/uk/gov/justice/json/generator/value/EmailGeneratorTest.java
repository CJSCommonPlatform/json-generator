package uk.gov.justice.json.generator.value;

import static org.junit.Assert.assertTrue;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

public class EmailGeneratorTest {

    @Test
    public void shouldGenerateARandomEmailInQuotes() throws Exception {
        EmailGenerator emailGenerator = new EmailGenerator();
        final String jsonEmailValue = emailGenerator.nextValue();
        assertTrue(EmailValidator.getInstance().isValid(jsonEmailValue));
    }
}