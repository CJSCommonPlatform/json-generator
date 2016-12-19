package uk.gov.justice.json.generators.values;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;

public class RandomDateTimeGeneratorTest {

    private final RandomDateTimeGenerator randomDateTimeGenerator = new RandomDateTimeGenerator();

    final String regexISO = "\\\"(19|20)[0-9][0-9]-(0[0-9]|1[0-2])-(0[1-9]|([012][0-9]|3[01])T([012][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9].([0-9]||[0-9][0-9]|[0-9][0-9][0-9]))\\\"";

    @Test
    public void randomDateTime() throws Exception {
        final String dateTimeISO = randomDateTimeGenerator.nextValue();
        final RegexValidator validator = new RegexValidator(regexISO);
        assertThat(true, is(validator.isValid(dateTimeISO)));
    }
}
