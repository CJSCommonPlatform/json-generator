package uk.gov.justice.json.formatting;

public class SimpleJsonPropertyFormatter {

    private static final String TEMPLATE = "\"%s\": %s";

    public String toJson(final String name, final Object value) {

        return String.format(TEMPLATE, name, value);
    }
}
