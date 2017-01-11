package uk.gov.justice.json.generation.generators.selectors.arrays;

import static java.util.Arrays.asList;

import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generation.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.BooleanValueGenerator;
import uk.gov.justice.json.generation.generators.values.IntegerValueGenerator;
import uk.gov.justice.json.generation.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generation.generators.values.StringValueGenerator;

import java.util.List;
import java.util.Map;

import org.everit.json.schema.EnumSchema;

public class BoundedArrayGeneratorSelector {

    public JsonPropertyGenerator createGenerator(final String propertyName, final Map<String, EnumSchema> items) {

        return new ArrayPropertyGenerator(
                propertyName,
                getListArrayGenerators(propertyName, items));
    }

    // @TODO: make these return a random number of generators rather than always three
    private List<JsonValueGenerator> getListArrayGenerators(final String propertyName, final Map<String, EnumSchema> items) {
        final String type = items.get("type").toString();

        switch (type) {
            case "string":
                return asList(
                        new StringValueGenerator(),
                        new StringValueGenerator(),
                        new StringValueGenerator()
                );
            case "integer":
                return asList(
                        new IntegerValueGenerator(),
                        new IntegerValueGenerator(),
                        new IntegerValueGenerator()
                );
            case "number":
                return asList(
                        new IntegerValueGenerator(),
                        new IntegerValueGenerator(),
                        new IntegerValueGenerator()
                );
            case "boolean":
                return asList(
                        new BooleanValueGenerator(),
                        new BooleanValueGenerator(),
                        new BooleanValueGenerator()
                );
            default:
                throw new JsonGenerationException("Failed to create generators for list array property '" + propertyName + "'. Unknown type '" + type + "");

        }
    }
}
