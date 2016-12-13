package uk.gov.justice.json.generation.generators.values;

import static uk.gov.justice.json.generation.Constants.COMMA;
import static uk.gov.justice.json.generation.Constants.LEFT_BRACKET;
import static uk.gov.justice.json.generation.Constants.RIGHT_BRACKET;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;

import java.util.List;

public class ArrayValueGenerator implements JsonValueGenerator {

    private final List<JsonPropertyGenerator> jsonPropertyGenerators;

    public ArrayValueGenerator(final List<JsonPropertyGenerator> jsonPropertyGenerators) {
        this.jsonPropertyGenerators = jsonPropertyGenerators;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public String nextValue() {

        final StringBuilder stringBuilder = new StringBuilder();
        jsonPropertyGenerators.forEach(propertyGenerator -> {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(COMMA);
            }
            stringBuilder.append(propertyGenerator.nextJson());
        });

        stringBuilder.insert(0, LEFT_BRACKET);
        stringBuilder.append(RIGHT_BRACKET);

        return stringBuilder.toString();
    }
}
