package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.formatting.JsonPropertyFormatter;
import uk.gov.justice.json.generation.generators.values.EmailValueGenerator;

import com.google.common.annotations.VisibleForTesting;

public class EmailPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final EmailValueGenerator emailValueGenerator;
    private final JsonPropertyFormatter quotedJsonPropertyFormatter;

    public EmailPropertyGenerator(final String name) {
        this(name, new EmailValueGenerator(), new JsonPropertyFormatter());
    }

    @VisibleForTesting
    EmailPropertyGenerator(
            final String name,
            final EmailValueGenerator emailValueGenerator,
            final JsonPropertyFormatter quotedJsonPropertyFormatter) {
        this.name = name;
        this.emailValueGenerator = emailValueGenerator;
        this.quotedJsonPropertyFormatter = quotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {
        return quotedJsonPropertyFormatter.toJson(name, emailValueGenerator.nextValue());
    }


}
