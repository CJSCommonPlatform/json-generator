package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

import org.everit.json.schema.Schema;

public class JsonValueGenerator extends Generator<JsonValue> {

    private Generator<? extends JsonValue> generator;

    public JsonValueGenerator(final Schema schema) {
        generator = generatorFor(schema);
    }

    @Override
    public JsonValue next() {
        return generator.next();
    }
}
