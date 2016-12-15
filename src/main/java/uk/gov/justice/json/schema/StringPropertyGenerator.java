package uk.gov.justice.json.schema;

import uk.gov.justice.json.generators.PropertyGenerator;
import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomStringGenerator;

import com.google.common.annotations.VisibleForTesting;

public class StringPropertyGenerator implements PropertyGenerator {

    private final String name;
    private final RandomStringGenerator randomStringGenerator;
    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter;

    public StringPropertyGenerator(final String name) {
        this(name, new RandomStringGenerator(), new QuotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    StringPropertyGenerator(
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
    public String next() {
        return quotedJsonPropertyFormatter.toJson(name, randomStringGenerator.randomString());
    }
}
