package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

public class BigDecimalGeneratorTest {
    @Test
    public void shouldGenerateBigDecimal() {
        final BigDecimalGenerator bigDecimalGenerator = BigDecimalGenerator.builder().build();
        assertThat(bigDecimalGenerator.next(), isA(BigDecimal.class));
    }

    @Test
    public void shouldLimitOnScale() {
        final Optional<BigDecimal> minimum = Optional.of(new BigDecimal(10.0));
        final int scale = 2;
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.minimum(minimum);
        builder.scale(scale);
        final BigDecimalGenerator bigDecimalGenerator = builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value, is(new GreaterOrEqual<BigDecimal>(minimum.get())));
        assertThat(value.scale(), is(scale));
    }

    @Test
    public void shouldLimitOnMinimum() {
        final Optional<BigDecimal> minimum = Optional.of(new BigDecimal(10.0));
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.minimum(minimum);
        final BigDecimalGenerator bigDecimalGenerator = builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value, is(new GreaterOrEqual<BigDecimal>(minimum.get())));
    }

    @Test
    public void shouldLimitOnExclusiveMinimum() {
        final Optional<BigDecimal> minimum = Optional.of(new BigDecimal(10.0));
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.minimum(minimum);
        builder.exclusiveMinimum(true);
        final BigDecimalGenerator bigDecimalGenerator = builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value, is(new GreaterThan<BigDecimal>(minimum.get())));
    }

    @Test
    public void shouldLimitOnMaximum() {
        final Optional<BigDecimal> maximum = Optional.of(new BigDecimal(200.0));
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.maximum(maximum);
        final BigDecimalGenerator integerGenerator = builder.build();
        final BigDecimal value = integerGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value, is(new LessOrEqual<BigDecimal>(maximum.get())));
    }

    @Test
    public void shouldLimitOnExclusiveMaximum() {
        final Optional<BigDecimal> maximum = Optional.of(new BigDecimal(100.0));
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.maximum(maximum);
        builder.exclusiveMaximum(true);
        final BigDecimalGenerator bigDecimalGenerator = builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value, is(new LessThan<BigDecimal>(maximum.get())));
    }

    @Test
    public void shouldLimitOnMultipleOff() {
        final Optional<BigDecimal> multiple = Optional.of(BigDecimal.TEN);
        final Optional<BigDecimal> minimum = Optional.of(new BigDecimal(10.0));
        final Optional<BigDecimal> maximum = Optional.of(new BigDecimal(200.0));
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.multipleOf(multiple);
        builder.minimum(minimum);
        builder.maximum(maximum);
        final BigDecimalGenerator bigDecimalGenerator = builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value.remainder(multiple.get()), is(new BigDecimal(0.0)));
    }

    @Test
    public void shouldLimitOnEverything() {
        final int scale =2;
        final Optional<BigDecimal> multiple = Optional.of(BigDecimal.TEN);
        final Optional<BigDecimal> minimum = Optional.of(new BigDecimal(10.0));
        final Optional<BigDecimal> maximum = Optional.of(new BigDecimal(200.0));
        final BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        builder.multipleOf(multiple);
        builder.minimum(minimum);
        builder.maximum(maximum);
        builder.exclusiveMinimum(true);
        builder.exclusiveMaximum(true);
        builder.scale(scale);
        final BigDecimalGenerator bigDecimalGenerator = builder.build();
        final BigDecimal value = bigDecimalGenerator.next();
        assertThat(value, isA(BigDecimal.class));
        assertThat(value.remainder(multiple.get()), is(new BigDecimal(0.0).setScale(scale)));
        assertThat(value.scale(), is(scale));
        assertThat(value, is(new GreaterThan<BigDecimal>(minimum.get())));
        assertThat(value, is(new LessThan<BigDecimal>(maximum.get())));
    }
}
