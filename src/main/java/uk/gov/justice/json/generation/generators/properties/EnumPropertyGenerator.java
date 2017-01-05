package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.EnumValueGenerator;

import java.util.List;

public class EnumPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<Object> enums;

    private JsonPropertyFormatter jsonPropertyFormatter = new JsonPropertyFormatter();

    public EnumPropertyGenerator(
            final String name,
            final List<Object> enums) {
        this.name = name;
        this.enums = enums;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        final String randomValue = new EnumValueGenerator(enums).nextValue();
        return jsonPropertyFormatter.toJson(name, randomValue);
    }
}
