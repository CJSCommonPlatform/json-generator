package uk.gov.justice.json.generators.selectors.arrays;

import static java.util.Arrays.asList;

import uk.gov.justice.json.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generators.values.RandomBooleanGenerator;
import uk.gov.justice.json.generators.values.RandomIntegerGenerator;
import uk.gov.justice.json.generators.values.RandomStringGenerator;

import java.util.List;

public class UnspecifiedArrayGeneratorSelector {

    public JsonPropertyGenerator createGenerator(final String propertyName) {

        // TODO: return a random number of generators of random types
        final List<JsonValueGenerator> generators = asList(
                new RandomStringGenerator(),
                new RandomBooleanGenerator(),
                new RandomIntegerGenerator()
        );

        return new ArrayPropertyGenerator(propertyName, generators);
    }
}
