package uk.gov.justice.json.generator.value;

import static org.junit.Assert.assertTrue;

import uk.gov.justice.json.generator.value.string.EmailGenerator;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

public class EmailGeneratorTest {

    @Test
    public void shouldGenerateARandomEmail() throws Exception {
        final EmailGenerator emailGenerator = new EmailGenerator();
        final String jsonEmailValue = emailGenerator.next();
        System.out.println(jsonEmailValue);
        assertTrue(EmailValidator.getInstance().isValid(jsonEmailValue));
    }

}
