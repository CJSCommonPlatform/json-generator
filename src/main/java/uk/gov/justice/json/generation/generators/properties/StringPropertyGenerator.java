package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import com.google.common.annotations.VisibleForTesting;

public class StringPropertyGenerator implements JsonPropertyGenerator {

    private final String name; //name not required, each should only know how to generate its value
    private final StringGenerator randomStringGenerator;
    private final JsonPropertyFormatter jsonPropertyFormatter;

    public StringPropertyGenerator(final String name) { //limits on string
        this(name, new StringGenerator(), new JsonPropertyFormatter());
    }

    @VisibleForTesting
    StringPropertyGenerator(
            final String name,
            final StringGenerator randomStringGenerator,
            final JsonPropertyFormatter jsonPropertyFormatter) {
        this.name = name;
        this.randomStringGenerator = randomStringGenerator;
        this.jsonPropertyFormatter = jsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return jsonPropertyFormatter.toJson(name, randomStringGenerator.next());
    }
}
