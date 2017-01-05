package uk.gov.justice.json.generation.generators.values;

import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

public class BooleanValueGenerator implements JsonValueGenerator {

    private BooleanGenerator booleanGenerator = new BooleanGenerator();

    @Override
    public String nextValue() {
        return booleanGenerator.next().toString();
    }
}
