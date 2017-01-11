package uk.gov.justice.json.generation.generators.selectors;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.everit.json.schema.CombinedSchema;

public class OneOfSelector {

    private final SelectorFactory selectorFactory = new SelectorFactory();
    private RandomListItemSelector randomListItemSelector = new RandomListItemSelector();

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator getOneOf(final String propertyName, final CombinedSchema schemas) {
        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();
        final PropertyGeneratorSelector propertyGeneratorSelector = selectorFactory.createNewPropertyGeneratorSelector();
        schemas.getSubschemas().forEach((schema) -> {
            jsonPropertyGenerators.add(propertyGeneratorSelector.createGenerator(schema.getTitle(), schema));
        });
        return jsonPropertyGenerators.get(new Random().nextInt(jsonPropertyGenerators.size()));
    }
}
