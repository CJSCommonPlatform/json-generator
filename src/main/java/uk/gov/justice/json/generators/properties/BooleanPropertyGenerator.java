package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.BooleanGenerator;

import com.google.common.annotations.VisibleForTesting;

public class BooleanPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final BooleanGenerator booleanGenerator;
    private final JsonPropertyFormatter jsonPropertyFormatter;

    public BooleanPropertyGenerator(final String name) {
        this(name, new BooleanGenerator(), new JsonPropertyFormatter());
    }

    @VisibleForTesting
    BooleanPropertyGenerator(
            final String name,
            final BooleanGenerator randomBooleanGenerator,
            final JsonPropertyFormatter jsonPropertyFormatter) {
        this.name = name;
        this.booleanGenerator = randomBooleanGenerator;
        this.jsonPropertyFormatter = jsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return jsonPropertyFormatter.toJson(name, booleanGenerator.next());
    }
}
