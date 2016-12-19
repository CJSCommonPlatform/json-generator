package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

import com.google.common.annotations.VisibleForTesting;

public class BooleanJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final BooleanGenerator booleanGenerator;
    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter;

    public BooleanJsonPropertyGenerator(final String name) {
        this(name, new BooleanGenerator(), new SimpleJsonPropertyFormatter());
    }

    @VisibleForTesting
    BooleanJsonPropertyGenerator(
            final String name,
            final BooleanGenerator randomBooleanGenerator,
            final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter) {
        this.name = name;
        this.booleanGenerator = randomBooleanGenerator;
        this.simpleJsonPropertyFormatter = simpleJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return simpleJsonPropertyFormatter.toJson(name, booleanGenerator.next());
    }
}
