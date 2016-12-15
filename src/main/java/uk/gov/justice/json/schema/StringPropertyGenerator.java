package uk.gov.justice.json.schema;

import static uk.gov.justice.json.Constants.COLON;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;
import static uk.gov.justice.json.Constants.SPACE;

import uk.gov.justice.json.PropertyGenerator;

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
