package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generator.value.string.HostNameGenerator;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;


public class HostNameGeneratorTest {

    @Test
    public void shouldGenerateHostName(){
        final HostNameGenerator hostNameGenerator = new HostNameGenerator();
        final String hostName = hostNameGenerator.next();
        final RegexValidator validator =new  RegexValidator(HostNameGenerator.PATTERN);
        assertThat(true, is(validator.isValid(hostName)));
    }

}
