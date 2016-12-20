package uk.gov.justice.json.generators.factories;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generators.values.RandomBooleanGenerator;
import uk.gov.justice.json.generators.values.RandomEnumValueGenerator;
import uk.gov.justice.json.generators.values.RandomIntegerGenerator;
import uk.gov.justice.json.generators.values.RandomStringGenerator;

import java.util.List;
import java.util.Map;

public class UnspecifiedArrayPropertyGeneratorFactory {

    public JsonPropertyGenerator createGenerator(final String propertyName) {

        final List<JsonValueGenerator> generators = asList(
                new RandomStringGenerator(),
                new RandomBooleanGenerator(),
                new RandomIntegerGenerator()
        );

        return new ArrayPropertyGenerator(propertyName, generators);
    }
}
