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

        if (items.size() == 1) {
            // create a random bunch of generators of the same type
            final String type = items.get("type").toString();
            final List<JsonValueGenerator> generators;

            switch (type) {
                case "string":
                    generators = asList(
                            new RandomStringGenerator(),
                            new RandomStringGenerator(),
                            new RandomStringGenerator()
                    );
                    break;
                case "integer":
                    generators = asList(
                            new RandomIntegerGenerator(),
                            new RandomIntegerGenerator(),
                            new RandomIntegerGenerator()
                    );
                    break;
                case "boolean":
                    generators = asList(
                            new RandomBooleanGenerator(),
                            new RandomBooleanGenerator(),
                            new RandomBooleanGenerator()
                    );
                    break;
                default:
                    throw new JsonGenerationException("Oh no");

            }

            return new ArrayPropertyGenerator(propertyName, generators);
        }

        return null;
    }
}
