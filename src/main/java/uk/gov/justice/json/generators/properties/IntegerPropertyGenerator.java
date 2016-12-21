package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomIntegerGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IntegerPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomIntegerGenerator randomIntegerGenerator;
    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter;

    public IntegerPropertyGenerator(final String name) {
        this(name, new RandomIntegerGenerator(), new SimpleJsonPropertyFormatter());
    }

    @VisibleForTesting
    IntegerPropertyGenerator(
            final String name,
            final RandomIntegerGenerator randomIntegerGenerator,
            final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter) {
        this.name = name;
        this.randomIntegerGenerator = randomIntegerGenerator;
        this.simpleJsonPropertyFormatter = simpleJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return simpleJsonPropertyFormatter.toJson(
                name,
                randomIntegerGenerator.nextValue());
    }
}
