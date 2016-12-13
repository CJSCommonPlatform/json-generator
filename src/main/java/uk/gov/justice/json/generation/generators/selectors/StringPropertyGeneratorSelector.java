package uk.gov.justice.json.generation.generators.selectors;

import uk.gov.justice.json.generation.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

import java.util.regex.Pattern;

import org.everit.json.schema.FormatValidator;
import org.everit.json.schema.StringSchema;

public class StringPropertyGeneratorSelector {

    private static final String UNNAMED_FORMAT = "unnamed-format";

    public JsonPropertyGenerator getPropertyGenerator(final String propertyName, final StringSchema stringSchema) {

        final FormatValidator formatValidator = stringSchema.getFormatValidator();
        final String formatName = formatValidator.formatName();

        final Pattern pattern = stringSchema.getPattern();
        if (formatName != null && formatName != UNNAMED_FORMAT) {
            if ("email".equals(formatName)) {
                return new EmailPropertyGenerator(propertyName);
            }
            if ("date-time".equals(formatName)) {
                return new IsoDateTimePropertyGenerator(propertyName);
            }
        }

        if (pattern != null) {
            return new RegexPropertyGenerator(propertyName, pattern);
        }

        return new StringPropertyGenerator(propertyName);
    }
}
