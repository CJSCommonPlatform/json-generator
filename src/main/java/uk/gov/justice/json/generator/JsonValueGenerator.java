package uk.gov.justice.json.generator;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

public abstract class JsonValueGenerator<T extends JsonValue> extends Generator<T> {

    public abstract T next();
}
