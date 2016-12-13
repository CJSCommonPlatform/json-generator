package uk.gov.justice.json.generation.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

import java.util.ArrayList;
import java.util.List;

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