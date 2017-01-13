package uk.gov.justice.json.generator.value;

import uk.gov.justice.json.generator.JsonValueGenerator;

import javax.json.JsonValue;

/**
 * Generator for an unspecified random JSON value.
 */
public class UnspecifiedJsonValueGenerator extends JsonValueGenerator<JsonValue> {

    @Override
    public JsonValue next() {
        // TODO: Implement this random generator generator
        return JsonValue.NULL;
    }
}
