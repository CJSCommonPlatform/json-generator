package uk.gov.justice.json.generator;

import uk.gov.justice.json.generator.value.EmailGenerator;
import uk.gov.justice.json.generator.value.IsoDateTimeGenerator;
import uk.gov.justice.json.generator.value.RegexGenerator;
import uk.gov.justice.json.generator.value.StringGenerator;

import javax.json.Json;
import javax.json.JsonString;

import org.everit.json.schema.FormatValidator;
import org.everit.json.schema.StringSchema;

public class JsonStringGenerator implements JsonValueGenerator {

    private static final String UNNAMED_FORMAT ="unnamed-format";

    private StringGenerator stringGenerator;

    public JsonStringGenerator(final StringSchema stringSchema) {

        final FormatValidator formatValidator= stringSchema.getFormatValidator();
        final String formatName =formatValidator.formatName();

        if (formatName!= null && formatName!= UNNAMED_FORMAT) {
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
        return Json.createObjectBuilder().add("tmp", string).build().getJsonString("tmp");
    }
}
