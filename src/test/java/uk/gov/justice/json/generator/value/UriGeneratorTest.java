package uk.gov.justice.json.generator.value;

import uk.gov.justice.json.generator.value.string.UriGenerator;

import org.junit.Test;

public class UriGeneratorTest {

    @Test
    public void shouldGenerateUri(){
        final String uriAddress = new UriGenerator().next();
//        final RegexValidator regexValidator = new RegexValidator(UriGenerator.EMAIL_PATTERN_REGEX, true);
//        assertThat(true, is(regexValidator.isValid(uriAddress)));
    }

}
