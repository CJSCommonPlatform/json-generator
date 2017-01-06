package uk.gov.justice.json.generation.generators.definitions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.schema.JsonGenerator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DefinitionsProviderTest {

    @InjectMocks
    DefinitionsProvider definitionsProvider;

   @Test
   public void shouldAddDefinitionGenerator() {
       final String definitionName = "UUID";
        final JsonPropertyGenerator jsonPropertyGenerator = mock(JsonPropertyGenerator.class);
        definitionsProvider.addPropertyGenerator(definitionName, jsonPropertyGenerator);
        assertThat(definitionsProvider.getDefinitions().size(), is(1));
    }

    @Test
    public void shouldGetDefinitionGenerator() {
        final String definitionName = "UUID";
        final JsonPropertyGenerator jsonPropertyGenerator = mock(JsonPropertyGenerator.class);
        definitionsProvider.addPropertyGenerator(definitionName, jsonPropertyGenerator);
        assertThat(definitionsProvider.getJsonPropertyGenerator(definitionName),is(jsonPropertyGenerator));
    }
}