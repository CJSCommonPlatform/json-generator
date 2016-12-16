package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

public class RegexJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final String pattern;
    private final RandomRegexStringGenerator randomRegexStringGenerator;

    private final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter;

    public RegexJsonPropertyGenerator(final String name, final String pattern) {
        this(name, pattern, new RandomRegexStringGenerator(), new QuotedJsonPropertyFormatter());
    }

    public RegexJsonPropertyGenerator(
            final String name,
            final String pattern,
            final RandomRegexStringGenerator randomRegexStringGenerator,
            final QuotedJsonPropertyFormatter quotedJsonPropertyFormatter) {
        this.name = name;
        this.pattern = pattern;
        this.randomRegexStringGenerator = randomRegexStringGenerator;
        this.quotedJsonPropertyFormatter = quotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return quotedJsonPropertyFormatter.toJson(name, randomRegexStringGenerator.randomString(pattern));
    }
}
