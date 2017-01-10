package uk.gov.justice.json.generator;

import javax.json.JsonValue;

/**
 * Created by david on 10/01/17.
 */
public interface JsonValueGenerator {

    JsonValue nextValue();
}
