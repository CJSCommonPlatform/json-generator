package uk.gov.justice.json.generators.factories;

public class FactoryProvider {

    public ObjectPropertyGeneratorFactory createNewObjectGeneratorFactory() {
        return new ObjectPropertyGeneratorFactory();
    }

    public BasicPropertyGeneratorFactory createNewPropertyGeneratorFactory() {
        return new BasicPropertyGeneratorFactory();
    }

    public ListArrayPropertyGeneratorFactory createNewListArrayPropertyGeneratorFactory() {
        return new ListArrayPropertyGeneratorFactory();
    }

    public TupleArrayPropertyGeneratorFactory createNewTupleArrayPropertyGeneratorFactory() {
        return new TupleArrayPropertyGeneratorFactory();
    }

    public UnspecifiedArrayPropertyGeneratorFactory createNewUnspecifiedArrayPropertyGeneratorFactory() {
        return new UnspecifiedArrayPropertyGeneratorFactory();
    }
}
