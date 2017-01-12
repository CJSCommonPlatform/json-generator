package uk.gov.justice.json.generation.schema;

import com.google.common.annotations.VisibleForTesting;

public class ValidatingJsonSchemaParser {

    private final JsonSchemaParser jsonSchemaParser;
    private final Validator validator;

    @VisibleForTesting
    public ValidatingJsonSchemaParser(final JsonSchemaParser jsonSchemaParser,
                                      final Validator validator) {
        this.jsonSchemaParser = jsonSchemaParser;
        this.validator = validator;
    }

    public void validateAndParse(final String schema) {
        validator.validate(schema);
        jsonSchemaParser.parse(schema);
    }
}
