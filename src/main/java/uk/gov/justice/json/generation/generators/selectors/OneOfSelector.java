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
import java.util.Random;
import java.util.Set;

public class OneOfSelector {

    private RandomListItemSelector randomListItemSelector = new RandomListItemSelector();
    private final SelectorFactory selectorFactory = new SelectorFactory();
    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator getOneOf(final String propertyName, final CombinedSchema schemas) {
        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();
        final PropertyGeneratorSelector propertyGeneratorSelector = selectorFactory.createNewPropertyGeneratorSelector();
        schemas.getSubschemas().forEach((schema) -> {
            jsonPropertyGenerators.add(propertyGeneratorSelector.createGenerator(schema.getTitle(), schema ));
        });
        return jsonPropertyGenerators.get(new Random().nextInt(jsonPropertyGenerators.size()));
    }
}
