package uk.gov.justice.json.generator.jsonvalue;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

/**
 * Generator for JsonBoolean values.
 */
public class NullJsonValueGenerator extends Generator<JsonValue> {

    @Override
    public JsonValue next() {
        return JsonValue.NULL;
    }
}
