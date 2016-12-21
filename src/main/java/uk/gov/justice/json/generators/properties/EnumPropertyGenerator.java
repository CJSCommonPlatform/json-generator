package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomEnumValueGenerator;

import java.util.List;

public class EnumPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<Object> enums;

    private SimpleJsonPropertyFormatter simpleJsonPropertyFormatter = new SimpleJsonPropertyFormatter();

    public EnumPropertyGenerator(
            final String name,
            final List<Object> enums) {
        this.name = name;
        this.enums = enums;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        final String randomValue = new RandomEnumValueGenerator(enums).nextValue();
        return simpleJsonPropertyFormatter.toJson(name, randomValue);
    }
}
