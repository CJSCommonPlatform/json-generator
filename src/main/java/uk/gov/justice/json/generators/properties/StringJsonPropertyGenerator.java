package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.SimpleJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import com.google.common.annotations.VisibleForTesting;

public class StringJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final StringGenerator randomStringGenerator;
    private final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter;

    public StringJsonPropertyGenerator(final String name) {
        this(name, new StringGenerator(), new SimpleJsonPropertyFormatter());
    }

    @VisibleForTesting
    StringJsonPropertyGenerator(
            final String name,
            final StringGenerator randomStringGenerator,
            final SimpleJsonPropertyFormatter simpleJsonPropertyFormatter) {
        this.name = name;
        this.randomStringGenerator = randomStringGenerator;
        this.simpleJsonPropertyFormatter = simpleJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return simpleJsonPropertyFormatter.toJson(name, randomStringGenerator.next());
    }
}
