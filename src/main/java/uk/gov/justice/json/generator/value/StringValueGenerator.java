package uk.gov.justice.json.generator.value;

import uk.gov.justice.services.test.utils.core.random.StringGenerator;

public class StringValueGenerator implements uk.gov.justice.json.generator.value.StringGenerator{

    private static final int LENGTH = 10;
    private StringGenerator stringGenerator = new StringGenerator(LENGTH);

    @Override
    public String nextValue() {
        return  stringGenerator.next();
    }
}
