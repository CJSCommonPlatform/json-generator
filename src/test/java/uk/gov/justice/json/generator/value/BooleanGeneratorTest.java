package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.bool.BooleanGenerator;

import org.junit.Test;

public class BooleanGeneratorTest {

    private final BooleanGenerator booleanGenerator= new BooleanGenerator();

    @Test
    public void shouldGeneratorARandomBoolean(){
        assertThat(booleanGenerator.next(),isA(Boolean.class));
    }
}
