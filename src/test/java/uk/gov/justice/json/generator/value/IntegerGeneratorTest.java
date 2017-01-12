package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class IntegerGeneratorTest {

    @Test
    public void shouldGenerateARandomInteger(){
        final IntegerGenerator integerGenerator = new IntegerGenerator();
        assertThat(integerGenerator.next(),isA(Integer.class));
    }

    @Test
    public void shouldLimitOnMinimum(){

        final IntegerGenerator integerGenerator = new IntegerGenerator();
        assertThat(integerGenerator.next(),isA(Integer.class));
    }

    @Test
    public void shouldLimitOnExclusiveMinimum(){
        fail();
    }

    @Test
    public void shouldLimitOnMaximum(){
        fail();
    }

    @Test
    public void shouldLimitOnExclusiveMaximum(){
        fail();
    }
}
