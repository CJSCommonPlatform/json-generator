package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

public class SimpleStringGenerator extends Generator<String> {

    @Override
    public String next() {
        return new StringGenerator().next();
    }
}
