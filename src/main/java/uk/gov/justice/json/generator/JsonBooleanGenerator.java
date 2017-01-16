package uk.gov.justice.json.generator;

import static javax.json.JsonValue.FALSE;
import static javax.json.JsonValue.TRUE;

import uk.gov.justice.json.generator.value.bool.BooleanGenerator;

import javax.json.JsonValue;

/**
 * Generator for JsonBoolean values.
 */
public class JsonBooleanGenerator extends JsonValueGenerator<JsonValue> {

    private final BooleanGenerator generator = new BooleanGenerator();

    @Override
    public JsonValue next() {
        return generator.next() ? TRUE : FALSE;
    }
}
