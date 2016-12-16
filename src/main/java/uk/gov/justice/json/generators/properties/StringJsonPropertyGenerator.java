package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import com.google.common.annotations.VisibleForTesting;

public class StringJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final StringGenerator randomStringGenerator;
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter;

    public StringJsonPropertyGenerator(final String name) {
        this(name, new StringGenerator(), new QuotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    StringJsonPropertyGenerator(
            final String name,
            final StringGenerator randomStringGenerator,
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
        return quotedJsonPropertyFormatter.toJson(name, randomStringGenerator.next());
    }
}
