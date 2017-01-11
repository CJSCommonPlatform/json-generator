package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

public class BigDecimalGeneratorTest {
    @Test
    public void shouldGenerateBigDecimal(){
        final BigDecimalGenerator bigDecimalGenerator =BigDecimalGenerator.builder().build();
        assertThat(bigDecimalGenerator.next(),isA(BigDecimal.class));
    }

    @Test
    public void shouldLimitOnScale(){
        final BigDecimal minimum = new BigDecimal(10.0);
        final int scale = 2;
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.minimum(minimum);
        builder.scale(scale);
        final BigDecimalGenerator bigDecimalGenerator =  builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value,isA(BigDecimal.class));
        assertThat(value,is(new GreaterOrEqual<BigDecimal>(minimum)));
        assertThat(value.scale(),is(scale));

    }

    @Test
    public void shouldLimitOnMinimum(){
        final BigDecimal minimum = new BigDecimal(10.0);
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.minimum(minimum);
        final BigDecimalGenerator bigDecimalGenerator =  builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value,isA(BigDecimal.class));
        assertThat(value,is(new GreaterOrEqual<BigDecimal>(minimum)));
    }

    @Test
    public void shouldLimitOnExclusiveMinimum(){
        final BigDecimal minimum = new BigDecimal(10.0);
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.minimum(minimum);
        builder.exclusiveMinimum(true);
        final BigDecimalGenerator bigDecimalGenerator =  builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value,isA(BigDecimal.class));
        assertThat(value,is(new GreaterThan<BigDecimal>(minimum)));
    }

    @Test
    public void shouldLimitOnMaximum(){
        final BigDecimal maximum = new BigDecimal(200.0);
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.maximum(maximum);
        final BigDecimalGenerator integerGenerator =  builder.build();
        final BigDecimal value = integerGenerator.next();
        assertThat(value,isA(BigDecimal.class));
        assertThat(value,is(new LessOrEqual<BigDecimal>(maximum)));
    }

    @Test
    public void shouldLimitOnExclusiveMaximum(){
        final BigDecimal maximum = new BigDecimal(100.0);
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.maximum(maximum);
        builder.exclusiveMaximum(true);
        final BigDecimalGenerator bigDecimalGenerator =  builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value,isA(BigDecimal.class));
        assertThat(value,is(new LessThan<BigDecimal>(maximum)));
    }

    @Test
    public void shouldLimitOnMultipleOff(){
        final BigDecimal multiple =BigDecimal.TEN;
        final BigDecimal minimum = new BigDecimal(10.0);
        final BigDecimal maximum = new BigDecimal(200.0);
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.multipleOf(multiple);
        builder.minimum(minimum);
        builder.maximum(maximum);
        final BigDecimalGenerator bigDecimalGenerator =  builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value,isA(BigDecimal.class));
        assertThat(value.remainder(multiple),is(new BigDecimal(0.0).setScale(1)));

    }
}
