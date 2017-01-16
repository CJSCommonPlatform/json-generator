package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;
import uk.gov.justice.services.test.utils.core.random.Generator;

public class EmailGenerator extends Generator<String> {

    private EmailAddressGenerator emailAddressGenerator;

    public EmailGenerator() {
        emailAddressGenerator = new EmailAddressGenerator();
    }

    @Override
    public String next() {
        return emailAddressGenerator.next();
    }
}
