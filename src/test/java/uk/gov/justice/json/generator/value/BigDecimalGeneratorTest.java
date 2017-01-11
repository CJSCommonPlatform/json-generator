package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import uk.gov.justice.services.test.utils.core.random.BigDecimalGenerator;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalGeneratorTest {
    @Test
    public void shouldGenerateBigDecimal(){

        assertThat(new BigDecimalGenerator().next(),isA(BigDecimal.class));
    }
}
