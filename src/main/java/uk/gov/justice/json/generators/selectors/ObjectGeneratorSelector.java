package uk.gov.justice.json.generators.selectors;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectPropertyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();

    @SuppressWarnings("unchecked")
    public ObjectPropertyGenerator createGenerator(final String propertyName, final Map<String, Object> properties) {

        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();
        final PropertyGeneratorSelector propertyGeneratorSelector = selectorFactory.createNewPropertyGeneratorSelector();

        properties.forEach((name, value) -> jsonPropertyGenerators.add(propertyGeneratorSelector.createGenerator(name, value)));

        return new ObjectPropertyGenerator(propertyName, jsonPropertyGenerators);
    }
}
