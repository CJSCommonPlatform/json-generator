package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomStringGenerator;

import com.google.common.annotations.VisibleForTesting;

public class StringJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomStringGenerator randomStringGenerator;
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter;

    public StringJsonPropertyGenerator(final String name) {
        this(name, new RandomStringGenerator(), new QuotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    StringJsonPropertyGenerator(
            final String name,
            final RandomStringGenerator randomStringGenerator,
            final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter) {
        this.name = name;
        this.randomStringGenerator = randomStringGenerator;
        this.quotedJsonPropertyFormatter = quotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return quotedJsonPropertyFormatter.toJson(name, randomStringGenerator.randomString());
    }
}
