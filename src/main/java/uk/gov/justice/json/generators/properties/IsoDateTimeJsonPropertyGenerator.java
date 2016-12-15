package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomDateTimeGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IsoDateTimeJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomDateTimeGenerator randomDateTimeGenerator;
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();

    public IsoDateTimeJsonPropertyGenerator(final String name) {
        this(name, new RandomDateTimeGenerator());
    }

    @VisibleForTesting
    IsoDateTimeJsonPropertyGenerator(
            final String name,
            final RandomDateTimeGenerator randomDateTimeGenerator) {
        this.name = name;
        this.randomDateTimeGenerator = randomDateTimeGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return quotedJsonPropertyFormatter.toJson(name, randomDateTimeGenerator.randomDateTime());
    }
}
