package uk.gov.justice.json.schema;

import static uk.gov.justice.json.Constants.COMMA;
import static uk.gov.justice.json.Constants.LEFT_BRACE;
import static uk.gov.justice.json.Constants.RIGHT_BRACE;

import uk.gov.justice.json.PropertyGenerator;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;

public class JsonDocumentGenerator {

    private final List<PropertyGenerator> propertyGenerators;

    public JsonDocumentGenerator(final List<PropertyGenerator> propertyGenerators) {
        this.propertyGenerators = propertyGenerators;
    }

    public String generate() {

        final StringBuilder stringBuilder = new StringBuilder();

        propertyGenerators.forEach(propertyGenerator -> {
            stringBuilder.append(propertyGenerator.next());
            if (stringBuilder.length() > 0) {
                stringBuilder.append(COMMA);
            }
        });

        stringBuilder.insert(0, LEFT_BRACE).append(RIGHT_BRACE);

        return stringBuilder.toString();
    }

    @VisibleForTesting
    List<PropertyGenerator> getPropertyGenerators() {
        return propertyGenerators;
    }
}
