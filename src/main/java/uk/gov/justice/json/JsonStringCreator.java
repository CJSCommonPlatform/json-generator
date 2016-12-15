package uk.gov.justice.json;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import java.time.ZonedDateTime;
import java.util.Map;

import com.mifmif.common.regex.Generex;

public class JsonStringCreator {

    private static final int STRING_LENGTH = 10;

    public String createRandomString(final Map<String, Object> jsonSchema) {
        String value;
        if (jsonSchema.containsKey("pattern")) {
            String pattern = (String) jsonSchema.get("pattern");
            //TODO fix the pattern properly
            pattern = pattern.replace('^', ' ').replace('$', ' ').trim();
            value = new Generex(pattern).random();
        } else if (jsonSchema.containsKey("format")) {
            final String format = (String) jsonSchema.get("format");
            if (format.equals("email")) {
                value = randomAlphanumeric(5) + "@test.com";
            } else if (format.equals("date-time")) {
                value = ZonedDateTime.now().format(ISO_DATE_TIME);
            } else {
                value = randomAlphanumeric(STRING_LENGTH);
            }
        } else {
            value = randomAlphanumeric(STRING_LENGTH);
        }
        return format("\"%s\"", value);
    }
}
