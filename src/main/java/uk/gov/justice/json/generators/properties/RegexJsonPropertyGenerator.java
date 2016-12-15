package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

public class RegexJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final String regex;
    private final RandomRegexStringGenerator randomRegexStringGenerator;

    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();

    public RegexJsonPropertyGenerator(final String name, final String pattern) {
        this(name, pattern, new RandomRegexStringGenerator());
    }

    public RegexJsonPropertyGenerator(final String name, final String pattern, final RandomRegexStringGenerator randomRegexStringGenerator) {
        this.name = name;
        this.regex = pattern;
        this.randomRegexStringGenerator = randomRegexStringGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return quotedJsonPropertyFormatter.toJson(name, randomRegexStringGenerator.randomString(regex));
    }
}
