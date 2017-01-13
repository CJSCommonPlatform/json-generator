package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;


public class Ipv6GeneratorTest {

    @Test
    public void shouldGenerateIpv6(){
        final Ipv6Generator ipv6Generator = new Ipv6Generator();
        final String ipAddress = ipv6Generator.next();
        final RegexValidator regexValidator = new RegexValidator(Ipv6Generator.pattern, true);
        assertThat(true, is(regexValidator.isValid(ipAddress)));
    }

}