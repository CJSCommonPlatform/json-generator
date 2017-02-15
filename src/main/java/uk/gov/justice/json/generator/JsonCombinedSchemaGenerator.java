package uk.gov.justice.json.generator;

import static java.util.stream.Collectors.toList;
import static org.everit.json.schema.CombinedSchema.ALL_CRITERION;
import static org.everit.json.schema.CombinedSchema.ANY_CRITERION;
import static org.everit.json.schema.CombinedSchema.ONE_CRITERION;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.List;

import javax.json.JsonValue;

import org.everit.json.schema.CombinedSchema;

public class JsonCombinedSchemaGenerator extends Generator<JsonValue> {

    private List<Generator<? extends JsonValue>> generators;

    public JsonCombinedSchemaGenerator(final CombinedSchema combinedSchema) {

        if (combinedSchema.getCriterion() == ONE_CRITERION) {
            throw new JsonGenerationException("oneOf combined schema type is not supported");
        } else if (combinedSchema.getCriterion() == ALL_CRITERION) {
            throw new JsonGenerationException("allOf combined schema type is not supported");
        } else if (combinedSchema.getCriterion() == ANY_CRITERION) {
            generators = combinedSchema.getSubschemas().stream()
                    .map(JsonValueGenerators::generatorFor)
                    .collect(toList());
        } else {
            throw new JsonGenerationException("Unsupported combined schema type");
        }
    }

    @Override
    public JsonValue next() {
        final int index = RANDOM.nextInt(generators.size());
        return generators.get(index).next();
    }
}
