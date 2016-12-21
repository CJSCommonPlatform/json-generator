package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RegexValueGenerator;

public class RegexPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final String pattern;
    private final RegexValueGenerator regexValueGenerator;

    private final JsonPropertyFormatter jsonPropertyFormatter;

    public RegexPropertyGenerator(final String name, final String pattern) {
        this(name, pattern, new RegexValueGenerator(pattern), new JsonPropertyFormatter());
    }

    public RegexPropertyGenerator(
            final String name,
            final String pattern,
            final RegexValueGenerator regexValueGenerator,
            final JsonPropertyFormatter jsonPropertyFormatter) {
        this.name = name;
        this.pattern = pattern;
        this.regexValueGenerator = regexValueGenerator;
        this.jsonPropertyFormatter = jsonPropertyFormatter;
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
        return jsonPropertyFormatter.toJson(name, regexValueGenerator.nextValue());
    }
}
