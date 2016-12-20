package uk.gov.justice.json.generators.values;

import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import uk.gov.justice.json.Constants;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

public class RandomStringGenerator implements JsonValueGenerator {

    private static final int LENGTH = 10;
    private StringGenerator stringGenerator = new StringGenerator(LENGTH);

    @Override
    public String nextValue() {
        return DOUBLE_QUOTE + stringGenerator.next() + DOUBLE_QUOTE;
    }
}
