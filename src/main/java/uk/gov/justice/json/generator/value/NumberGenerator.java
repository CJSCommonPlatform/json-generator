package uk.gov.justice.json.generator.value;


public interface NumberGenerator<T extends Number> extends PrimitiveGenerator {

    T nextValue();
}
