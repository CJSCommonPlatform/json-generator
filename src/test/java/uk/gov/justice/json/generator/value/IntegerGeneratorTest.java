package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

public class IntegerGeneratorTest {

    @Test
    public void shouldGenerateARandomInteger(){
        final IntegerGenerator integerGenerator =IntegerGenerator.builder().build();
        assertThat(integerGenerator.next(),isA(Integer.class));
    }

    @Test
    public void shouldLimitOnMinimum(){
        final int minimum =10;
        final int maximum =13;

        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.minimum(minimum);
        final IntegerGenerator integerGenerator =  builder.build();
        final int value = integerGenerator.next();
        assertThat(value,isA(Integer.class));
        assertThat(value,is(new GreaterOrEqual<Integer>(minimum)));
    }

    @Test
    public void shouldLimitOnExclusiveMinimum(){
        final int minimum = 10;
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.minimum(minimum);
        builder.exclusiveMinimum(true);
        final IntegerGenerator integerGenerator =  builder.build();
        final int value = integerGenerator.next();
        assertThat(value,isA(Integer.class));
        assertThat(value,is(new GreaterThan<Integer>(minimum)));
    }

    @Test
    public void shouldLimitOnMaximum(){
        final int maximum = 100;
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.maximum(maximum);
        final IntegerGenerator integerGenerator =  builder.build();
        final int value = integerGenerator.next();
        assertThat(value,isA(Integer.class));
        assertThat(value,is(new LessOrEqual<Integer>(maximum)));
    }

    @Test
    public void shouldLimitOnExclusiveMaximum(){
        final int maximum = 100;
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.maximum(maximum);
        builder.exclusiveMaximum(true);
        final IntegerGenerator integerGenerator =  builder.build();
        final int value = integerGenerator.next();
        assertThat(value,isA(Integer.class));
        assertThat(value,is(new LessThan<Integer>(maximum)));
    }

    @Test
    public void shouldLimitOnMultipleOff(){
        final int multiple =10;
        final int minimum = 0;
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.multipleOf(multiple);
        builder.minimum(minimum);
        final IntegerGenerator integerGenerator =  builder.build();
        final int value = integerGenerator.next();
        assertThat(value,isA(Integer.class));
        assertThat(value % multiple,is(0));
    }

    @Test
    public void shouldLimitOnEverything(){
        final int multiple =10;
        final int minimum = 0;
        final int maximum = 200;
        final IntegerGenerator.Builder builder = IntegerGenerator.builder();
        builder.multipleOf(multiple);
        builder.exclusiveMinimum(true);
        builder.exclusiveMaximum(true);
        builder.minimum(minimum);
        builder.maximum(maximum);
        final IntegerGenerator integerGenerator =  builder.build();
        final int value = integerGenerator.next();
        assertThat(value,isA(Integer.class));
        assertThat(value % multiple,is(0));
        assertThat(value,is(new GreaterThan<Integer>(minimum)));
        assertThat(value,is(new LessThan<Integer>(maximum)));

    }
}
