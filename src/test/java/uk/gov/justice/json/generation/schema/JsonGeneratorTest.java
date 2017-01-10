package uk.gov.justice.json.generation.schema;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonGeneratorTest {

    final List<JsonPropertyGenerator> propertyGenerators = new ArrayList<>();

    final JsonGenerator jsonGenerator = new  JsonGenerator(propertyGenerators);

    @Test
    public void shouldGenerate(){
        jsonGenerator.getJsonPropertyGenerators().add(new StringPropertyGenerator("string"));

        jsonGenerator.generate();



    }
}