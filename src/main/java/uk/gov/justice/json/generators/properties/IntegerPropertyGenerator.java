package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generators.values.IntegerValueGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IntegerPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final IntegerValueGenerator integerValueGenerator;
    private final JsonPropertyFormatter jsonPropertyFormatter;

    public IntegerPropertyGenerator(final String name) {
        this(name, new IntegerValueGenerator(), new JsonPropertyFormatter());
    }

    @VisibleForTesting
    IntegerPropertyGenerator(
            final String name,
            final IntegerValueGenerator integerValueGenerator,
            final JsonPropertyFormatter jsonPropertyFormatter) {
        this.name = name;
        this.integerValueGenerator = integerValueGenerator;
        this.jsonPropertyFormatter = jsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return jsonPropertyFormatter.toJson(
                name,
                integerValueGenerator.nextValue());
    }
}
