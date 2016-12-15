package uk.gov.justice.json.schema;

public class UnquotedJsonPropertyFormatter {

    private static final String TEMPLATE = "\"%s\": %s";

    public String toJson(final String name, final Object value) {

        return String.format(TEMPLATE, name, value);
    }
}
