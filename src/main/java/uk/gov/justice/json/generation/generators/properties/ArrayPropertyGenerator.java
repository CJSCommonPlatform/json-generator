package uk.gov.justice.json.generation.generators.properties;

import static uk.gov.justice.json.generation.Constants.COMMA;
import static uk.gov.justice.json.generation.Constants.LEFT_BRACKET;
import static uk.gov.justice.json.generation.Constants.RIGHT_BRACKET;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.JsonValueGenerator;

import java.util.List;

public class ArrayPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<JsonValueGenerator> jsonValueGenerators;

    public ArrayPropertyGenerator(final String name, final List<JsonValueGenerator> jsonValueGenerators) {
        this.name = name;
        this.jsonValueGenerators = jsonValueGenerators;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {

        final StringBuilder stringBuilder = new StringBuilder();

        jsonValueGenerators
                .forEach(jsonValueGenerator -> appendNextValue(stringBuilder, jsonValueGenerator));

        stringBuilder.insert(0, LEFT_BRACKET);
        stringBuilder.append(RIGHT_BRACKET);

        return new JsonPropertyFormatter().toJson(name, stringBuilder.toString());
    }

    private StringBuilder appendNextValue(final StringBuilder stringBuilder, final JsonValueGenerator jsonValueGenerator) {

        if (stringBuilder.length() > 0) {
            stringBuilder.append(COMMA);
        }

        return stringBuilder.append(jsonValueGenerator.nextValue());
    }

}
