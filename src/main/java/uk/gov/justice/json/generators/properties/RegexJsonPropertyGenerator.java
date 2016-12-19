package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

public class RegexJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final String pattern;
    private final RandomRegexStringGenerator randomRegexStringGenerator;

    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter;

    public RegexJsonPropertyGenerator(final String name, final String pattern) {
        this(name, pattern, new RandomRegexStringGenerator(pattern), new UnquotedJsonPropertyFormatter());
    }

    public RegexJsonPropertyGenerator(
            final String name,
            final String pattern,
            final RandomRegexStringGenerator randomRegexStringGenerator,
            final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter) {
        this.name = name;
        this.pattern = pattern;
        this.randomRegexStringGenerator = randomRegexStringGenerator;
        this.unquotedJsonPropertyFormatter = unquotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public String nextJson() {
        return unquotedJsonPropertyFormatter.toJson(name, randomRegexStringGenerator.nextValue());
    }
}
