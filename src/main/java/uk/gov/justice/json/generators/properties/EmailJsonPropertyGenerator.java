package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomEmailGenerator;

import com.google.common.annotations.VisibleForTesting;

public class EmailJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final RandomEmailGenerator randomEmailGenerator;
    private final UnquotedJsonPropertyFormatter quotedJsonPropertyFormatter;

    public EmailJsonPropertyGenerator(final String name) {
        this(name, new RandomEmailGenerator(), new UnquotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    EmailJsonPropertyGenerator(
            final String name,
            final RandomEmailGenerator randomEmailGenerator,
            final UnquotedJsonPropertyFormatter quotedJsonPropertyFormatter) {
        this.name = name;
        this.randomEmailGenerator = randomEmailGenerator;
        this.quotedJsonPropertyFormatter = quotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return quotedJsonPropertyFormatter.toJson(name, randomEmailGenerator.nextValue());
    }
}
