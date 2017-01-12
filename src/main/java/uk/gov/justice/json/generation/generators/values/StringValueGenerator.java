package uk.gov.justice.json.generation.generators.values;

import uk.gov.justice.services.test.utils.core.random.StringGenerator;

public class StringValueGenerator implements JsonValueGenerator {

    private static final int LENGTH = 10;
    private StringGenerator stringGenerator = new StringGenerator(LENGTH);

    @Override
    public String nextValue() {
        return stringGenerator.next();
    }
}
