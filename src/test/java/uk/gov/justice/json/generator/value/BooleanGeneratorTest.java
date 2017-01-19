package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BooleanGeneratorTest {

    private final BooleanGenerator booleanGenerator= new BooleanGenerator();

    @Test
    public void shouldGeneratorNextValue(){
        assertThat(booleanGenerator.nextValue(),isA(Boolean.class));
    }
}
