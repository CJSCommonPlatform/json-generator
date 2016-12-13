package uk.gov.justice.json.generation.generators.selectors.arrays;

import static java.util.Arrays.asList;

import uk.gov.justice.json.generation.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.BooleanValueGenerator;
import uk.gov.justice.json.generation.generators.values.IntegerValueGenerator;
import uk.gov.justice.json.generation.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generation.generators.values.StringValueGenerator;

import java.util.List;

public class UnspecifiedArrayGeneratorSelector {

    public JsonPropertyGenerator createGenerator(final String propertyName) {

        // TODO: return a random number of generators of random types
        final List<JsonValueGenerator> generators = asList(
                new StringValueGenerator(),
                new BooleanValueGenerator(),
                new IntegerValueGenerator()
        );

        return new ArrayPropertyGenerator(propertyName, generators);
    }
}
