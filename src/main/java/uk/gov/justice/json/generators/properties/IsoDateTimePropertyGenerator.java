package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomDateTimeGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IsoDateTimePropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomDateTimeGenerator randomDateTimeGenerator;
    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter;

    public IsoDateTimePropertyGenerator(final String name) {
        this(name, new RandomDateTimeGenerator(), new SimpleJsonPropertyFormatter());
    }

    @VisibleForTesting
    IsoDateTimePropertyGenerator(
            final String name,
            final RandomDateTimeGenerator randomDateTimeGenerator,
            final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter) {
        this.name = name;
        this.randomDateTimeGenerator = randomDateTimeGenerator;
        this.simpleJsonPropertyFormatter = simpleJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return simpleJsonPropertyFormatter.toJson(name, randomDateTimeGenerator.nextValue());
    }
}
