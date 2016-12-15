package uk.gov.justice.json.schema;

import static uk.gov.justice.json.Constants.COLON;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;
import static uk.gov.justice.json.Constants.SPACE;

import uk.gov.justice.json.PropertyGenerator;

import com.google.common.annotations.VisibleForTesting;

public class BooleanPropertyGenerator implements PropertyGenerator {

    private final String name;
    private final RandomBooleanGenerator randomBooleanGenerator;
    private final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter;

    public BooleanPropertyGenerator(final String name) {
        this(name, new RandomBooleanGenerator(), new UnquotedJsonPropertyFormatter());
    }

    @VisibleForTesting
    BooleanPropertyGenerator(
            final String name,
            final RandomBooleanGenerator randomBooleanGenerator,
            final UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter) {
        this.name = name;
        this.randomBooleanGenerator = randomBooleanGenerator;
        this.unquotedJsonPropertyFormatter = unquotedJsonPropertyFormatter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String next() {
        return unquotedJsonPropertyFormatter.toJson(name, randomBooleanGenerator.randomBoolean());
    }
}
