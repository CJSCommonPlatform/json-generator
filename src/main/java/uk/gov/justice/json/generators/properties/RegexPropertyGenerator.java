package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomRegexStringGenerator;

public class RegexPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final String pattern;
    private final RandomRegexStringGenerator randomRegexStringGenerator;

    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter;

    public RegexPropertyGenerator(final String name, final String pattern) {
        this(name, pattern, new RandomRegexStringGenerator(pattern), new SimpleJsonPropertyFormatter());
    }

    public RegexPropertyGenerator(
            final String name,
            final String pattern,
            final RandomRegexStringGenerator randomRegexStringGenerator,
            final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter) {
        this.name = name;
        this.pattern = pattern;
        this.randomRegexStringGenerator = randomRegexStringGenerator;
        this.simpleJsonPropertyFormatter = simpleJsonPropertyFormatter;
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
        return simpleJsonPropertyFormatter.toJson(name, randomRegexStringGenerator.nextValue());
    }
}
