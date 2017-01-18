package uk.gov.justice.json.generator;

import static org.everit.json.schema.CombinedSchema.*;
import static uk.gov.justice.json.generator.JsonValueGenerators.generatorFor;

import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonValue;

import org.everit.json.schema.CombinedSchema;
import org.everit.json.schema.Schema;

public class JsonCombinedSchemaGenerator extends Generator<JsonValue> {

    private Generator<? extends JsonValue> generator;

    public JsonCombinedSchemaGenerator(CombinedSchema combinedSchema) {

        if(combinedSchema.getCriterion() == ONE_CRITERION ) {
            throw new JsonGenerationException("oneOf combined schema type is not supported");
        }

        if(combinedSchema.getCriterion() == ALL_CRITERION ) {
            throw new JsonGenerationException("allOf combined schema type is not supported");
        }

        if(combinedSchema.getCriterion() == ANY_CRITERION ) {
            getAnyOf(combinedSchema);
        }
    }

    private void getAnyOf(CombinedSchema combinedSchema) {
        final Generator<Schema> schemaGenerator =
                new ValueGenerator(combinedSchema.getSubschemas());
        generator = generatorFor(schemaGenerator.next());
    }

    @Override
    public JsonValue next() {
        return generator.next();
    }
}
