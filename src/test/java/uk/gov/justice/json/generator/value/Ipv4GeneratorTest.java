package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.string.Ipv4Generator;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;


public class Ipv4GeneratorTest {

    @Test
    public void shouldGenerateIpv4() {
        final Ipv4Generator ipv4Generator = new Ipv4Generator();
        final String ipAddress = ipv4Generator.next();
        final RegexValidator regexValidator = new RegexValidator(Ipv4Generator.PATTERN, true);
        assertThat(true, is(regexValidator.isValid(ipAddress)));
    }
}
