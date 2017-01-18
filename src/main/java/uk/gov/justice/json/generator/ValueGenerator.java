package uk.gov.justice.json.generator;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ValueGenerator extends Generator {

    private final Collection possibleValues;
    private final int value;

    public ValueGenerator(Collection possibleValues) {
        this.possibleValues = possibleValues;
        final int size = this.possibleValues.size();
        Random random = new Random();
        value = random.nextInt(size);
    }

    @Override
    public Object next() {
        return new ArrayList<>(possibleValues).get(value);
    }
}