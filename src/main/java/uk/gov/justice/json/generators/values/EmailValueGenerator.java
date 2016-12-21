package uk.gov.justice.json.generators.values;

import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

public class EmailValueGenerator implements JsonValueGenerator {

    private EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();

    @Override
    public String nextValue() {
        return DOUBLE_QUOTE + emailAddressGenerator.next() + DOUBLE_QUOTE;
    }
}
