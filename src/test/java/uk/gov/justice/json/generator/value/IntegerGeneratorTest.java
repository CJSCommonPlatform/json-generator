package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.number.IntegerGenerator;

import java.util.Optional;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

public class IntegerGeneratorTest {

    @Test
    public void shouldGenerateARandomInteger() {
        final IntegerGenerator integerGenerator = IntegerGenerator.builder().build();
        assertThat(integerGenerator.next(), isA(Integer.class));
    }

    @Test
    public void shouldLimitOnMinimum() {
        final Optional<Integer> minimum = Optional.of(10);
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.minimum(minimum);
        final IntegerGenerator integerGenerator = builder.build();
        final int value = integerGenerator.next();
        assertThat(value, isA(Integer.class));
        assertThat(value, is(new GreaterOrEqual<Integer>(minimum.get())));
    }

    @Test
    public void shouldLimitOnExclusiveMinimum() {
        final Optional<Integer> minimum = Optional.of(10);
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.minimum(minimum);
        builder.exclusiveMinimum(true);
        final IntegerGenerator integerGenerator = builder.build();
        final int value = integerGenerator.next();
        assertThat(value, isA(Integer.class));
        assertThat(value, is(new GreaterThan<Integer>(minimum.get())));
    }

    @Test
    public void shouldLimitOnMaximum() {
        final Optional<Integer> maximum = Optional.of(100);
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.maximum(maximum);
        final IntegerGenerator integerGenerator = builder.build();
        final int value = integerGenerator.next();
        assertThat(value, isA(Integer.class));
        assertThat(value, is(new LessOrEqual<Integer>(maximum.get())));
    }

    @Test
    public void shouldLimitOnExclusiveMaximum() {
        final Optional<Integer> maximum = Optional.of(100);
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.maximum(maximum);
        builder.exclusiveMaximum(true);
        final IntegerGenerator integerGenerator = builder.build();
        final int value = integerGenerator.next();
        assertThat(value, isA(Integer.class));
        assertThat(value, is(new LessThan<Integer>(maximum.get())));
    }

    @Test
    public void shouldLimitOnMultipleOff() {
        final Optional<Integer> multiple = Optional.of(10);
        final Optional<Integer> minimum = Optional.of(0);
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.multipleOf(multiple);
        builder.minimum(minimum);
        final IntegerGenerator integerGenerator = builder.build();
        final int value = integerGenerator.next();
        assertThat(value, isA(Integer.class));
        assertThat(value % multiple.get(), is(0));
    }

    @Test
    public void shouldLimitOnEverything() {
        final Optional<Integer> multiple = Optional.of(10);
        final Optional<Integer> minimum = Optional.of(0);
        final Optional<Integer> maximum = Optional.of(200);
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.multipleOf(multiple);
        builder.exclusiveMinimum(true);
        builder.exclusiveMaximum(true);
        builder.minimum(minimum);
        builder.maximum(maximum);
        final IntegerGenerator integerGenerator = builder.build();
        final int value = integerGenerator.next();
        assertThat(value, isA(Integer.class));
        assertThat(value % multiple.get(), is(0));
        assertThat(value, is(new GreaterThan<Integer>(minimum.get())));
        assertThat(value, is(new LessThan<Integer>(maximum.get())));
    }
}
