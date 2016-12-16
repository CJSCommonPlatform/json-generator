package uk.gov.justice.json.generators.factories;

public class FactoryProvider {

    public ObjectPropertyGeneratorFactory createNewObjectGeneratorFactory() {
        return new ObjectPropertyGeneratorFactory();
    }

    public SimplePropertyGeneratorFactory createNewPropertyGeneratorFactory() {
        return new SimplePropertyGeneratorFactory();
    }
}
