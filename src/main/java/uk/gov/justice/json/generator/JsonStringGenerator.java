package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.value.EmailGenerator;
import uk.gov.justice.json.generator.value.IsoDateTimeGenerator;
import uk.gov.justice.json.generator.value.RegexGenerator;
import uk.gov.justice.json.generator.value.SimpleStringGenerator;
import uk.gov.justice.json.generator.value.StringGenerator;

import javax.json.JsonString;

import org.everit.json.schema.FormatValidator;
import org.everit.json.schema.StringSchema;

public class JsonStringGenerator implements JsonValueGenerator<JsonString> {

    private static final String UNNAMED_FORMAT = "unnamed-format";

    private final String propertyName;
    private StringGenerator stringGenerator;

    public JsonStringGenerator(final String propertyName ,final StringSchema stringSchema) {

        this.propertyName = propertyName;
        this.stringGenerator = new SimpleStringGenerator();

        final FormatValidator formatValidator = stringSchema.getFormatValidator();
        final String formatName = formatValidator.formatName();

        if (formatName != null && formatName != UNNAMED_FORMAT) {
            if ("email".equals(formatName)) {
                stringGenerator = new EmailGenerator();
            }
            if ("date-time".equals(formatName)) {
                stringGenerator = new IsoDateTimeGenerator();
            }
        }

        if (stringSchema.getPattern() != null) {
            stringGenerator = new RegexGenerator(stringSchema.getPattern());
        }
    }

    @Override
    public JsonString nextValue() {
        return constructJsonString(stringGenerator.nextValue());
    }

    private JsonString constructJsonString(final String string) {
        return createObjectBuilder().add(propertyName, string).build().getJsonString(propertyName);
    }
}
