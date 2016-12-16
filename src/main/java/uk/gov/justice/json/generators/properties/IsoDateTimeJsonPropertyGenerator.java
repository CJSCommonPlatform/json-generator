package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomDateTimeGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IsoDateTimeJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomDateTimeGenerator randomDateTimeGenerator;
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter;

    public IsoDateTimeJsonPropertyGenerator(final String name) {
        this(name, new RandomDateTimeGenerator(), new QuotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    IsoDateTimeJsonPropertyGenerator(
            final String name,
            final RandomDateTimeGenerator randomDateTimeGenerator,
            final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter) {
        this.name = name;
        this.randomDateTimeGenerator = randomDateTimeGenerator;
        this.quotedJsonPropertyFormatter = quotedJsonPropertyFormatter;
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
