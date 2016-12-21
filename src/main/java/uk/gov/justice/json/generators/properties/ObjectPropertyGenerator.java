package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generators.values.ObjectValueGenerator;

import java.util.List;

public class ObjectPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<JsonPropertyGenerator> jsonPropertyGenerators;

    public ObjectPropertyGenerator(
            final String name,
            final List<JsonPropertyGenerator> jsonPropertyGenerators) {
        this.name = name;
        this.jsonPropertyGenerators = jsonPropertyGenerators;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<JsonPropertyGenerator> getJsonPropertyGenerators() {
        return jsonPropertyGenerators;
    }

    @Override
    public String nextJson() {
        final ObjectValueGenerator objectValueGenerator = new ObjectValueGenerator(jsonPropertyGenerators);
        final JsonPropertyFormatter jsonPropertyFormatter = new JsonPropertyFormatter();

        return jsonPropertyFormatter.toJson(name, objectValueGenerator.nextValue());
    }
}
