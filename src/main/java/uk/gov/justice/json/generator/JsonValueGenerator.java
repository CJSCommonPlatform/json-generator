package uk.gov.justice.json.generator;

import javax.json.JsonValue;

public interface JsonValueGenerator<T extends JsonValue>{

    T  nextValue();
}
