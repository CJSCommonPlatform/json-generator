package uk.gov.justice.json.generator;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

import org.everit.json.schema.NotSchema;

public class JsonNotSchemaGenerator extends Generator<JsonValue> {


    public JsonNotSchemaGenerator(final NotSchema schema) {
        throw new JsonGenerationException("Json schema 'not' keyword is not supported");
    }

    @Override
    public JsonValue next() {
        throw new UnsupportedOperationException("Json schema 'not' keyword is not supported");
    }
}
