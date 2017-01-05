package uk.gov.justice.json.generation.schema;

import static uk.gov.justice.json.generation.Constants.COMMA;
import static uk.gov.justice.json.generation.Constants.LEFT_BRACE;
import static uk.gov.justice.json.generation.Constants.RIGHT_BRACE;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;

public class JsonDocumentGenerator {

    private final List<JsonPropertyGenerator> jsonPropertyGenerators;

    public JsonDocumentGenerator(final List<JsonPropertyGenerator> jsonPropertyGenerators) {
        this.jsonPropertyGenerators = jsonPropertyGenerators;
    }

    public String generate() {

        final StringBuilder stringBuilder = new StringBuilder();

        jsonPropertyGenerators.forEach(propertyGenerator -> {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(COMMA);
            }
            stringBuilder.append(propertyGenerator.nextJson());
        });

        stringBuilder.insert(0, LEFT_BRACE).append(RIGHT_BRACE);

        return stringBuilder.toString();
    }

    @VisibleForTesting
    List<JsonPropertyGenerator> getJsonPropertyGenerators() {
        return jsonPropertyGenerators;
    }
}
