package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonString;

import uk.gov.justice.json.generator.value.string.EmailGenerator;
import uk.gov.justice.json.generator.value.string.HostNameGenerator;
import uk.gov.justice.json.generator.value.string.Ipv4Generator;
import uk.gov.justice.json.generator.value.string.Ipv6Generator;
import uk.gov.justice.json.generator.value.string.IsoDateTimeGenerator;
import uk.gov.justice.json.generator.value.string.RegexGenerator;
import uk.gov.justice.json.generator.value.string.SimpleStringGenerator;
import uk.gov.justice.json.generator.value.string.UriGenerator;
import uk.gov.justice.services.test.utils.core.random.Generator;

import javax.json.JsonString;
import javax.json.JsonValue;

import org.everit.json.schema.FormatValidator;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.everit.json.schema.internal.HostnameFormatValidator;
import org.everit.json.schema.internal.IPV4Validator;
import org.everit.json.schema.internal.IPV6Validator;
import org.everit.json.schema.internal.URIFormatValidator;

public class JsonStringGenerator extends Generator<JsonValue> {

    private final Generator<String> stringGenerator;

    public JsonStringGenerator(final StringSchema stringSchema) {

        final FormatValidator formatValidator = stringSchema.getFormatValidator();

        if (formatValidator instanceof EmailFormatValidator) {
            stringGenerator = new EmailGenerator();
        } else if (formatValidator instanceof DateTimeFormatValidator) {
            stringGenerator = new IsoDateTimeGenerator();
        } else if (formatValidator instanceof HostnameFormatValidator) {
            stringGenerator = new HostNameGenerator();
        } else if (formatValidator instanceof URIFormatValidator) {
            stringGenerator = new UriGenerator();
        } else if (formatValidator instanceof IPV4Validator) {
            stringGenerator = new Ipv4Generator();
        } else if (formatValidator instanceof IPV6Validator) {
            stringGenerator = new Ipv6Generator();
        } else if (stringSchema.getPattern() != null) {
            stringGenerator = new RegexGenerator(stringSchema.getPattern());
        } else {
            int min =0;
            int max =0;
            if (stringSchema.getMinLength()!=null) {
                 min = stringSchema.getMinLength();
            }
            if (stringSchema.getMaxLength()!=null) {
                max = stringSchema.getMaxLength();
            }
            stringGenerator = new SimpleStringGenerator(min, max);
        }
    }

    @Override
    public JsonString next() {
        return buildJsonString(stringGenerator.next());
    }
}
