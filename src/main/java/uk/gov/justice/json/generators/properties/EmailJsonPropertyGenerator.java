package uk.gov.justice.json.generators.properties;

import static uk.gov.justice.json.Constants.COLON;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;
import static uk.gov.justice.json.Constants.SPACE;

public class EmailJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;

    public EmailJsonPropertyGenerator(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return DOUBLE_QUOTE + name + DOUBLE_QUOTE + COLON + SPACE + DOUBLE_QUOTE + "fred.bloggs@gerritt.com" + DOUBLE_QUOTE;
    }
}
