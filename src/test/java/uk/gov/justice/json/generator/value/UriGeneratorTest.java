package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;

public class UriGeneratorTest {

    @Test
    public void shouldGenerateUri(){
        final String uriAddress = new uk.gov.justice.json.generator.value.UriGenerator().next();
//        final RegexValidator regexValidator = new RegexValidator(UriGenerator.pattern, true);
//        assertThat(true, is(regexValidator.isValid(uriAddress)));
    }

}