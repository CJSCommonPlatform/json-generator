package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

import com.google.common.annotations.VisibleForTesting;

public class BooleanJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final BooleanGenerator booleanGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter;

    public BooleanJsonPropertyGenerator(final String name) {
        this(name, new BooleanGenerator(), new UnquotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    BooleanJsonPropertyGenerator(
            final String name,
            final BooleanGenerator randomBooleanGenerator,
            final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter) {
        this.name = name;
        this.booleanGenerator = randomBooleanGenerator;
        this.unquotedJsonPropertyFormatter = unquotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return unquotedJsonPropertyFormatter.toJson(name, booleanGenerator.next());
    }
}
