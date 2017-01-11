package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.RootObjectValueGenerator;

public class RootObjectPropertyGenerator implements JsonPropertyGenerator {
    private final String name;

    private final RootObjectValueGenerator rootObjectValueGenerator;

    private final JsonPropertyFormatter jsonPropertyFormatter;

    public RootObjectPropertyGenerator(final String name) {
        this(name, new RootObjectValueGenerator(), new JsonPropertyFormatter());
    }

    public RootObjectPropertyGenerator(
            final String name,
            final RootObjectValueGenerator rootObjectValueGenerator,
            final JsonPropertyFormatter jsonPropertyFormatter) {
        this.name = name;
        this.rootObjectValueGenerator = rootObjectValueGenerator;
        this.jsonPropertyFormatter = jsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String nextJson() {
        return jsonPropertyFormatter.toJson(name, rootObjectValueGenerator.nextValue());
    }
}
