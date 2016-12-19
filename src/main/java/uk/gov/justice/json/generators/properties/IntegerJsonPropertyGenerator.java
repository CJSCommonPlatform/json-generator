package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomIntegerGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IntegerJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomIntegerGenerator randomIntegerGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter;

    public IntegerJsonPropertyGenerator(final String name) {
        this(name, new RandomIntegerGenerator(), new UnquotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    IntegerJsonPropertyGenerator(
            final String name,
            final RandomIntegerGenerator randomIntegerGenerator,
            final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter) {
        this.name = name;
        this.randomIntegerGenerator = randomIntegerGenerator;
        this.unquotedJsonPropertyFormatter = unquotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return unquotedJsonPropertyFormatter.toJson(
                name,
                randomIntegerGenerator.nextValue());
    }
}
