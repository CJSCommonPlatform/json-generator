package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

import org.everit.json.schema.ReferenceSchema;

public class JsonReferenceGenerator extends Generator<JsonValue> {

    private final Generator<? extends JsonValue> generator;

    public JsonReferenceGenerator(final ReferenceSchema referenceSchema) {
        generator = generatorFor(referenceSchema.getReferredSchema());
    }

    @Override
    public JsonValue next() {
        return generator.next();
    }

}
