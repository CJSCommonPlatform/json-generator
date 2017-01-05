package uk.gov.justice.json.generation.generators.values;

import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import uk.gov.justice.services.test.utils.core.random.EmailAddressGenerator;

public class EmailValueGenerator implements JsonValueGenerator {

    private EmailAddressGenerator emailAddressGenerator = new EmailAddressGenerator();

    @Override
    public String nextValue() {
        return DOUBLE_QUOTE + emailAddressGenerator.next() + DOUBLE_QUOTE;
    }
}
