package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntegerGeneratorTest {

    @Test
    public void shouldGenerateIntegerNextValue(){

        assertThat(new IntegerGenerator().nextValue(),isA(Integer.class));
    }
}
