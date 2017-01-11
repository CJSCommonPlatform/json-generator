package uk.gov.justice.json.generator.value;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

public class EmailGenerator implements StringGenerator{

    private EmailAddressGenerator emailAddressGenerator;

    public EmailGenerator() {
        emailAddressGenerator = new EmailAddressGenerator();
    }

    @Override
    public String next() {
        return emailAddressGenerator.next();
    }
}
