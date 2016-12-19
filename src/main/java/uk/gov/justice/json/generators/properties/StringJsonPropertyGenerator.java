package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import com.google.common.annotations.VisibleForTesting;

public class StringJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final StringGenerator randomStringGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter;

    public StringJsonPropertyGenerator(final String name) {
        this(name, new StringGenerator(), new UnquotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    StringJsonPropertyGenerator(
            final String name,
            final StringGenerator randomStringGenerator,
            final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter) {
        this.name = name;
        this.randomStringGenerator = randomStringGenerator;
        this.unquotedJsonPropertyFormatter = unquotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return unquotedJsonPropertyFormatter.toJson(name, randomStringGenerator.next());
    }
}
