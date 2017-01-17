package uk.gov.justice.json.generator.value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.string.IsoDateTimeGenerator;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;

public class IsoDateTimeGeneratorTest {
    private final IsoDateTimeGenerator dateTimeGenerator = new IsoDateTimeGenerator();
    @Test
    public void shouldGenerateARandomDateTime() throws Exception {
        final String dateTimeISO = dateTimeGenerator.next();
        final RegexValidator validator = new RegexValidator(dateTimeISO);
        assertThat(true, is(validator.isValid(dateTimeISO)));
    }
}
