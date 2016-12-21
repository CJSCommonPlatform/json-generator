package uk.gov.justice.json.generators.selectors;

import uk.gov.justice.json.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringPropertyGenerator;

import java.util.Map;

public class StringPropertyGeneratorSelector {

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator getStringPropertyGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {

        final String format = (String) propertyDefinitions.get("format");
        final String pattern = (String) propertyDefinitions.get("pattern");

        if (format != null) {
            if ("email".equals(format)) {
                return new EmailPropertyGenerator(propertyName);
            }
            if ("date-time".equals(format)) {
                return new IsoDateTimePropertyGenerator(propertyName);
            }
        }

        if (pattern != null) {
            return new RegexPropertyGenerator(propertyName, pattern);
        }

        return new StringPropertyGenerator(propertyName);
    }
}
