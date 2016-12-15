package uk.gov.justice.json.schema;

import uk.gov.justice.json.generators.PropertyGenerator;
import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

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
