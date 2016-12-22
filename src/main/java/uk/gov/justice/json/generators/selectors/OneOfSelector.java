package uk.gov.justice.json.generators.selectors;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generators.values.ObjectValueGenerator;
import uk.gov.justice.json.generators.values.RandomListItemSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OneOfSelector {

    private RandomListItemSelector randomListItemSelector = new RandomListItemSelector();

    @SuppressWarnings("unchecked")
    public ObjectPropertyGenerator getOneOf(final String propertyName, final List<Object> schemas) {

        final Map<String, Object> objectDefinition = (Map<String, Object>) randomListItemSelector.selectRandomlyFrom(schemas);
        final Map<String, Object> properties = (Map<String, Object>) objectDefinition.get("properties");

        final List<JsonPropertyGenerator> propertyGenerators = new ArrayList<>();
        properties.forEach((name, value) -> {
            final JsonPropertyGenerator jsonPropertyGenerator = new PropertyGeneratorSelector().createGenerator(name, value);
            propertyGenerators.add(jsonPropertyGenerator);

        });

        return new ObjectPropertyGenerator(propertyName, propertyGenerators);
    }
}
