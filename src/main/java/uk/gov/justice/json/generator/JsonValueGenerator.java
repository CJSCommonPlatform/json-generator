package uk.gov.justice.json.generator;

import javax.json.JsonValue;

public interface JsonValueGenerator<T extends JsonValue> {
    static final String PROPERTY_NAME = "PROPERTY_NAME";

    T next();
}
