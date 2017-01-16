package uk.gov.justice.json.generator.jsonvalue;

import static javax.json.JsonValue.FALSE;
import static javax.json.JsonValue.TRUE;

import uk.gov.justice.json.generator.value.bool.BooleanGenerator;
import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

/**
 * Generator for JsonBoolean values.
 */
public class AnyJsonValueGenerator extends Generator<JsonValue> {

    private final BooleanGenerator generator = new BooleanGenerator();

    @Override
    public JsonValue next() {
        return generator.next() ? TRUE : FALSE;
    }
}
