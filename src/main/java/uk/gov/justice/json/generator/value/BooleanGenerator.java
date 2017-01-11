package uk.gov.justice.json.generator.value;

public class BooleanGenerator implements PrimitiveGenerator<Boolean> {

    public Boolean next() {
        return new uk.gov.justice.services.test.utils.core.random.BooleanGenerator().next();
    }
}
