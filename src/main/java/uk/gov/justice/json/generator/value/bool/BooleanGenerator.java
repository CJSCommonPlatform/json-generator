package uk.gov.justice.json.generator.value.bool;

import uk.gov.justice.services.test.utils.core.random.Generator;

public class BooleanGenerator extends Generator<Boolean> {

    @Override
    public Boolean next() {
        return new uk.gov.justice.services.test.utils.core.random.BooleanGenerator().next();
    }
}
