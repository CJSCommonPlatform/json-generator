package uk.gov.justice.json.generators.properties;

import static uk.gov.justice.json.Constants.COMMA;

import uk.gov.justice.json.formatting.CurlyBracedJsonPropertyFormatter;

import java.util.List;

public class ObjectJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<JsonPropertyGenerator> jsonPropertyGenerators;
    private final CurlyBracedJsonPropertyFormatter curlyBracedJsonPropertyFormatter;

    public ObjectJsonPropertyGenerator(final String name, final List<JsonPropertyGenerator> jsonPropertyGenerators) {
        this(name, jsonPropertyGenerators, new CurlyBracedJsonPropertyFormatter());
    }

    public ObjectJsonPropertyGenerator(
            final String name,
            final List<JsonPropertyGenerator> jsonPropertyGenerators,
            final CurlyBracedJsonPropertyFormatter curlyBracedJsonPropertyFormatter) {
        this.name = name;
        this.jsonPropertyGenerators = jsonPropertyGenerators;
        this.curlyBracedJsonPropertyFormatter = curlyBracedJsonPropertyFormatter;
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

        final StringBuilder stringBuilder = new StringBuilder();

        jsonPropertyGenerators.forEach(propertyGenerator -> {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(COMMA);
            }
            stringBuilder.append(propertyGenerator.nextJson());
        });

        return curlyBracedJsonPropertyFormatter.toJson(name, stringBuilder.toString());
    }
}
