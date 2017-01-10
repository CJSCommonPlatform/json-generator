package uk.gov.justice.json.generator.value;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

public class EmailGenerator implements StringGenerator{
    private EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();

    @Override
    public String nextValue() {
        return emailAddressGenerator.next();
    }

}
