package uk.gov.justice.json.generators.selectors.arrays;

import static java.util.Arrays.asList;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generators.values.RandomBooleanGenerator;
import uk.gov.justice.json.generators.values.RandomIntegerGenerator;
import uk.gov.justice.json.generators.values.RandomStringGenerator;

import java.util.List;
import java.util.Map;

public class ListArrayGeneratorSelector {

    public JsonPropertyGenerator createGenerator(final String propertyName, final Map<String, Object> items) {

        return new ArrayPropertyGenerator(
                propertyName,
                getListArrayGenerators(propertyName, items));
    }

    // @TODO: make these return a random number of generators rather than always three
    private List<JsonValueGenerator> getListArrayGenerators(final String propertyName, final Map<String, Object> items) {
        final String type = items.get("type").toString();

        switch (type) {
            case "string":
                return asList(
                        new RandomStringGenerator(),
                        new RandomStringGenerator(),
                        new RandomStringGenerator()
                );
            case "integer":
                return asList(
                        new RandomIntegerGenerator(),
                        new RandomIntegerGenerator(),
                        new RandomIntegerGenerator()
                );
            case "boolean":
                return asList(
                        new RandomBooleanGenerator(),
                        new RandomBooleanGenerator(),
                        new RandomBooleanGenerator()
                );
            default:
                throw new JsonGenerationException("Failed to create generators for list array property '" + propertyName + "'. Unknown type '" + type + "");

        }
    }
}
