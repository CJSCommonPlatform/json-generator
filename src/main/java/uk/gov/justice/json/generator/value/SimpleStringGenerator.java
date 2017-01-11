package uk.gov.justice.json.generator.value;

import uk.gov.justice.services.test.utils.core.random.StringGenerator;

public class SimpleStringGenerator implements uk.gov.justice.json.generator.value.StringGenerator {

    @Override
    public String next() {
        return new StringGenerator().next();
    }
}
