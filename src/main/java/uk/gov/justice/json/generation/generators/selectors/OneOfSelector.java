package uk.gov.justice.json.generation.generators.selectors;

import org.everit.json.schema.CombinedSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OneOfSelector {

    private RandomListItemSelector randomListItemSelector = new RandomListItemSelector();
    private final SelectorFactory selectorFactory = new SelectorFactory();
    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator getOneOf(final String propertyName, final CombinedSchema schemas) {

        final ObjectSchema objectSchema =  randomListItemSelector.selectRandomlyFrom(schemas);
        final Map<String,Schema> propertySchemas =objectSchema.getPropertySchemas();
        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();
        final PropertyGeneratorSelector propertyGeneratorSelector = selectorFactory.createNewPropertyGeneratorSelector();
        propertySchemas.forEach((name, value) -> jsonPropertyGenerators.add(propertyGeneratorSelector.createGenerator(name, value)));
        return jsonPropertyGenerators.get(0);
    }
}
