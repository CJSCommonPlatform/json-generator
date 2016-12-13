package uk.gov.justice.json.generation.generators.values;

import static uk.gov.justice.json.generation.Constants.COMMA;
import static uk.gov.justice.json.generation.Constants.LEFT_BRACE;
import static uk.gov.justice.json.generation.Constants.RIGHT_BRACE;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;

import java.util.List;

public class ObjectValueGenerator implements JsonValueGenerator {

    private final List<JsonPropertyGenerator>  jsonPropertyGenerators;

    public ObjectValueGenerator(final List<JsonPropertyGenerator> jsonPropertyGenerators) {
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

        stringBuilder.insert(0, LEFT_BRACE);
        stringBuilder.append(RIGHT_BRACE);

        return stringBuilder.toString();
    }
}
