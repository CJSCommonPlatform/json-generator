package uk.gov.justice.json.generation.generators.selectors.arrays;

import static java.util.stream.Collectors.toList;

import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.Schema;
import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generation.generators.properties.ArrayPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.BooleanValueGenerator;
import uk.gov.justice.json.generation.generators.values.EnumValueGenerator;
import uk.gov.justice.json.generation.generators.values.IntegerValueGenerator;
import uk.gov.justice.json.generation.generators.values.JsonValueGenerator;
import uk.gov.justice.json.generation.generators.values.StringValueGenerator;

import java.util.List;
import java.util.Map;

public class TupleArrayGeneratorSelector {

    public JsonPropertyGenerator createGenerator(final String propertyName, final List<Schema> items) {


        final List<JsonValueGenerator> generators = items.stream()
                .map(this::createGenerator)
                .collect(toList());

        return new ArrayPropertyGenerator(propertyName, generators);
    }

    @SuppressWarnings("unchecked")
    private JsonValueGenerator createGenerator(final Schema schema) {


        switch (schema.getClass().getSimpleName()) {
            case "StringSchema":
                return new StringValueGenerator();
            case "BooleanSchema":
                return new BooleanValueGenerator();
            case "NumberSchema":
                return new IntegerValueGenerator();
            case "EnumSchema":
                return new EnumValueGenerator(((EnumSchema)schema).getPossibleValues());
        }

        throw new JsonGenerationException("oh dear");
    }
}
