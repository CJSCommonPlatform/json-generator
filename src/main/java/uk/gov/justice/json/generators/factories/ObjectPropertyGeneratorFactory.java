package uk.gov.justice.json.generators.factories;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectJsonPropertyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectPropertyGeneratorFactory {

    private FactoryProvider factoryProvider = new FactoryProvider();

    @SuppressWarnings("unchecked")
    public ObjectJsonPropertyGenerator createGenerator(final String propertyName, final Object propertyValue) {

        final Map<String, Object> properties = (Map<String, Object>) propertyValue;
        final List<JsonPropertyGenerator> jsonPropertyGenerators = new ArrayList<>();
        final BasicPropertyGeneratorFactory basicPropertyGeneratorFactory = factoryProvider.createNewPropertyGeneratorFactory();

        properties.forEach((name, value) -> {
            jsonPropertyGenerators.add(basicPropertyGeneratorFactory.createGenerator(name, value));
        });


        return new ObjectJsonPropertyGenerator(propertyName, jsonPropertyGenerators);
    }
}
