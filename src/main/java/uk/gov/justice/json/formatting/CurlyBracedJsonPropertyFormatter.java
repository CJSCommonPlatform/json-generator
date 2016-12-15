package uk.gov.justice.json.formatting;

public class CurlyBracedJsonPropertyFormatter {

    private static final String TEMPLATE = "\"%s\": { %s }";

    public String toJson(final String name, final String value) {

        return String.format(TEMPLATE, name, value);
    }
}
