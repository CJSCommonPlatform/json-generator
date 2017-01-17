package uk.gov.justice.json.generator;


import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

import org.everit.json.schema.ReferenceSchema;
import org.everit.json.schema.Schema;

public class JsonReferenceGenerator extends Generator<JsonValue> {

    private final Schema referrencedSchema;


    public JsonReferenceGenerator(ReferenceSchema referenceSchema) {
        referrencedSchema = referenceSchema.getReferredSchema();
    }

    @Override

    public JsonValue next() {
        return generatorFor(referrencedSchema).next();
    }

}
