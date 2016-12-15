package uk.gov.justice.json.schema;

import static uk.gov.justice.json.Constants.COMMA;
import static uk.gov.justice.json.Constants.LEFT_BRACE;
import static uk.gov.justice.json.Constants.RIGHT_BRACE;

import uk.gov.justice.json.PropertyGenerator;

import java.util.ArrayList;
import java.util.List;

public class ObjectPropertyGenerator implements PropertyGenerator {

    private final String name;

    private final List<PropertyGenerator> propertyGenerators = new ArrayList<>();

    public ObjectPropertyGenerator(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String next() {

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

    public ObjectPropertyGenerator addPropertyGenerator(final PropertyGenerator propertyGenerator) {
        propertyGenerators.add(propertyGenerator);
        return this;
    }
}
