package uk.gov.justice.json.schema;

import static uk.gov.justice.json.Constants.COLON;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;
import static uk.gov.justice.json.Constants.SPACE;

import uk.gov.justice.json.PropertyGenerator;

public class RegexPropertyGenerator implements PropertyGenerator {

    private final String name;
    private final String regex;
    private final RandomRegexStringGenerator randomRegexStringGenerator;

    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();

    public RegexPropertyGenerator(final String name, final String pattern) {
        this(name, pattern, new RandomRegexStringGenerator());
    }

    public RegexPropertyGenerator(final String name, final String pattern, final RandomRegexStringGenerator randomRegexStringGenerator) {
        this.name = name;
        this.regex = pattern;
        this.randomRegexStringGenerator = randomRegexStringGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String next() {
        return quotedJsonPropertyFormatter.toJson(name, randomRegexStringGenerator.randomString(regex));
    }
}
