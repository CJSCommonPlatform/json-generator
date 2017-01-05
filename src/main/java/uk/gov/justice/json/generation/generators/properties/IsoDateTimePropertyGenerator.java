package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.DateTimeValueGenerator;

import com.google.common.annotations.VisibleForTesting;

public class IsoDateTimePropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final DateTimeValueGenerator dateTimeValueGenerator;
    private final JsonPropertyFormatter jsonPropertyFormatter;

    public IsoDateTimePropertyGenerator(final String name) {
        this(name, new DateTimeValueGenerator(), new JsonPropertyFormatter());
    }

    @VisibleForTesting
    IsoDateTimePropertyGenerator(
            final String name,
            final DateTimeValueGenerator dateTimeValueGenerator,
            final JsonPropertyFormatter jsonPropertyFormatter) {
        this.name = name;
        this.dateTimeValueGenerator = dateTimeValueGenerator;
        this.jsonPropertyFormatter = jsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return jsonPropertyFormatter.toJson(name, dateTimeValueGenerator.nextValue());
    }
}
