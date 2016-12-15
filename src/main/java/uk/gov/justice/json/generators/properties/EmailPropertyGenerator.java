package uk.gov.justice.json.generators.properties;

import static uk.gov.justice.json.Constants.COLON;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;
import static uk.gov.justice.json.Constants.SPACE;

import uk.gov.justice.json.generators.PropertyGenerator;

public class EmailPropertyGenerator implements PropertyGenerator {

    private final String name;

    public EmailPropertyGenerator(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String next() {
        return DOUBLE_QUOTE + name + DOUBLE_QUOTE + COLON + SPACE + DOUBLE_QUOTE + "fred.bloggs@gerritt.com" + DOUBLE_QUOTE;
    }
}
