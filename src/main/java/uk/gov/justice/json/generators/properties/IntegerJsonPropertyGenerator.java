package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.generators.values.RandomIntegerGenerator;
import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;

public class IntegerJsonPropertyGenerator implements JsonPropertyGenerator {


    private final String name;
    private final RandomIntegerGenerator randomIntegerGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = new UnquotedJsonPropertyFormatter();

    public IntegerJsonPropertyGenerator(final String name) {
        this(name, new RandomIntegerGenerator());
    }


    public IntegerJsonPropertyGenerator(final String name, final RandomIntegerGenerator randomIntegerGenerator) {
        this.name = name;
        this.randomIntegerGenerator = randomIntegerGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return unquotedJsonPropertyFormatter.toJson(
                name,
                randomIntegerGenerator.randomInt());
    }
}
