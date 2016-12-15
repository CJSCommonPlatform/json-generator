package uk.gov.justice.json.generators.factories;

public class FactoryProvider {

    public ObjectPropertyGeneratorFactory createNewObjectFactory() {
        return new ObjectPropertyGeneratorFactory();
    }

    public SimplePropertyGeneratorFactory createNewPropertyFactory() {
        return new SimplePropertyGeneratorFactory();
    }
}
