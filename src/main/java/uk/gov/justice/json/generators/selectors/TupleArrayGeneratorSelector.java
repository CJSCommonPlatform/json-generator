package uk.gov.justice.json.generators.selectors;

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

public class TupleArrayGeneratorSelector {

    public JsonPropertyGenerator createGenerator(final String propertyName, final List<Map<String, Object>> items) {


        final List<JsonValueGenerator> generators = items.stream()
                .map(this::createGenerator)
                .collect(toList());

        return new ArrayPropertyGenerator(propertyName, generators);
    }

    @SuppressWarnings("unchecked")
    private JsonValueGenerator createGenerator(final Map<String, Object> propertyDefinitions) {

        if (propertyDefinitions.containsKey("enum")) {
            final List<Object> enums = (List<Object>) propertyDefinitions.get("enum");
            return new RandomEnumValueGenerator(enums);
        }

        final String type = (String) propertyDefinitions.get("type");

        switch (type) {
            case "string":
                return new RandomStringGenerator();
            case "boolean":
                return new RandomBooleanGenerator();
            case "integer":
                return new RandomIntegerGenerator();
        }


        throw new JsonGenerationException("oh dear");
    }
}
