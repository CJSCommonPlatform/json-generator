package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomDateTimeGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IsoDateTimeJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomDateTimeGenerator randomDateTimeGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter;

    public IsoDateTimeJsonPropertyGenerator(final String name) {
        this(name, new RandomDateTimeGenerator(), new UnquotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    IsoDateTimeJsonPropertyGenerator(
            final String name,
            final RandomDateTimeGenerator randomDateTimeGenerator,
            final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter) {
        this.name = name;
        this.randomDateTimeGenerator = randomDateTimeGenerator;
        this.unquotedJsonPropertyFormatter = unquotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return unquotedJsonPropertyFormatter.toJson(name, randomDateTimeGenerator.nextValue());
    }
}
