package uk.gov.justice.json.schema;

import uk.gov.justice.json.PropertyGenerator;

public class IntegerPropertyGenerator implements PropertyGenerator {


    private final String name;
    private final RandomIntegerGenerator randomIntegerGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = new UnquotedJsonPropertyFormatter();

    public IntegerPropertyGenerator(final String name) {
        this(name, new RandomIntegerGenerator());
    }


    public IntegerPropertyGenerator(final String name, final RandomIntegerGenerator randomIntegerGenerator) {
        this.name = name;
        this.randomIntegerGenerator = randomIntegerGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String next() {
        return unquotedJsonPropertyFormatter.toJson(
                name,
                randomIntegerGenerator.randomInt());
    }
}
