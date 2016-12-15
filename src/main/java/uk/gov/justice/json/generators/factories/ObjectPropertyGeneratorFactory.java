package uk.gov.justice.json.generators.factories;

import uk.gov.justice.json.generators.properties.ObjectJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;

public class ObjectPropertyGeneratorFactory {

    private final FactoryProvider factoryProvider;

    public ObjectPropertyGeneratorFactory() {
        this(new FactoryProvider());
    }

    @VisibleForTesting
    ObjectPropertyGeneratorFactory(final FactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, Object> properties = (Map<String, Object>) value;

        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();

        properties.forEach((name, values) -> {
            jsonPropertyGenerators.add(factoryProvider.createNewPropertyFactory().createGenerator(name, values));
        });

        return new ObjectJsonPropertyGenerator(propertyName, jsonPropertyGenerators);
    }
}
