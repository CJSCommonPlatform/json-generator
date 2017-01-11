package uk.gov.justice.json.generation.generators.selectors;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.everit.json.schema.Schema;

public class ObjectGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();

    @SuppressWarnings("unchecked")
    public ObjectPropertyGenerator createGenerator(final String propertyName, final Map<String, Schema> properties) {

        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();
        final PropertyGeneratorSelector propertyGeneratorSelector = selectorFactory.createNewPropertyGeneratorSelector();

        properties.forEach((name, value) -> jsonPropertyGenerators.add(propertyGeneratorSelector.createGenerator(name, value)));

        return null;//new ObjectPropertyGenerator(propertyName, jsonPropertyGenerators);
    }
}
