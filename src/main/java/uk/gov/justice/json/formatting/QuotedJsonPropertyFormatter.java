package uk.gov.justice.json.formatting;

public class QuotedJsonPropertyFormatter {

    private static final String TEMPLATE = "\"%s\": \"%s\"";

    public String toJson(final String name, final String value) {

        return String.format(TEMPLATE, name, value);
    }
}
