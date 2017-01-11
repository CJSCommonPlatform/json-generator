package uk.gov.justice.json.generator.value;

public class BooleanGenerator implements PrimitiveGenerator<Boolean> {

    private uk.gov.justice.services.test.utils.core.random.BooleanGenerator booleanGenerator = new uk.gov.justice.services.test.utils.core.random.BooleanGenerator();

    public Boolean nextValue() {
        return booleanGenerator.next();
    }
}
