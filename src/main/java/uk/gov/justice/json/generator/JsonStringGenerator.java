package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonString;

import uk.gov.justice.json.generator.value.EmailGenerator;
import uk.gov.justice.json.generator.value.HostNameGenerator;
import uk.gov.justice.json.generator.value.Ipv6Generator;
import uk.gov.justice.json.generator.value.IsoDateTimeGenerator;
import uk.gov.justice.json.generator.value.RegexGenerator;
import uk.gov.justice.json.generator.value.SimpleStringGenerator;
import uk.gov.justice.json.generator.value.StringGenerator;
import uk.gov.justice.json.generator.value.UriGenerator;

import javax.json.JsonString;

import org.everit.json.schema.FormatValidator;
import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.everit.json.schema.internal.HostnameFormatValidator;
import org.everit.json.schema.internal.IPV4Validator;
import org.everit.json.schema.internal.IPV6Validator;
import org.everit.json.schema.internal.URIFormatValidator;

public class JsonStringGenerator extends JsonValueGenerator<JsonString> {

    private static final String UNNAMED_FORMAT = "unnamed-format";

    private StringGenerator stringGenerator;

    public JsonStringGenerator(final StringSchema stringSchema) {

        this.stringGenerator = new SimpleStringGenerator();

        final FormatValidator formatValidator = stringSchema.getFormatValidator();

        if (formatValidator instanceof EmailFormatValidator) {
            stringGenerator = new EmailGenerator();
        }
        if (formatValidator instanceof DateTimeFormatValidator) {
            stringGenerator = new IsoDateTimeGenerator();
        }
        if (formatValidator instanceof HostnameFormatValidator) {
            stringGenerator = new HostNameGenerator();
        }
//        if (formatValidator instanceof URIFormatValidator) {
//            stringGenerator = new UriGenerator();
//        }
//        if (formatValidator instanceof IPV4Validator) {
//            stringGenerator = new Ipv6Generator();
//        }
//        if (formatValidator instanceof IPV6Validator) {
//            stringGenerator = new EmailGenerator();
//        }

     if (stringSchema.getPattern() != null) {
            stringGenerator = new RegexGenerator(stringSchema.getPattern());
     }
    }

    @Override
    public JsonString next() {
        return buildJsonString(stringGenerator.next());
    }
}
