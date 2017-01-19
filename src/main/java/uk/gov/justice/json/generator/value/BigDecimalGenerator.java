package uk.gov.justice.json.generator.value;

import java.math.BigDecimal;

public class BigDecimalGenerator implements NumberGenerator<BigDecimal>{

    public BigDecimal next() {
        return new uk.gov.justice.services.test.utils.core.random.BigDecimalGenerator().next();
    }
}
