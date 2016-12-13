package uk.gov.justice.json.generation.generators.properties;

import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.Schema;
import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.EnumValueGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EnumPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<Object> enums;

    private JsonPropertyFormatter jsonPropertyFormatter = new JsonPropertyFormatter();

    public EnumPropertyGenerator(
            final String name,
            final Set<Object> enums) {
        this.name = name;
        this.enums = new ArrayList(enums);
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
