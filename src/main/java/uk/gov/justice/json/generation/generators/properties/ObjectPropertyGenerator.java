package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.ObjectValueGenerator;

import java.util.List;

public class ObjectPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<JsonPropertyGenerator> jsonPropertyGenerators;

    private final JsonPropertyFormatter jsonPropertyFormatter = new JsonPropertyFormatter();

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

    public List<JsonPropertyGenerator> getJsonProøpertyGenerators() {
        return jsonPropertyGenerators;
    }

    @Override
    public String nextJson() {
        final ObjectValueGenerator objectValueGenerator = new ObjectValueGenerator(jsonPropertyGenerators);

        return jsonPropertyFormatter.toJson(name, objectValueGenerator.nextValue());
    }
}
