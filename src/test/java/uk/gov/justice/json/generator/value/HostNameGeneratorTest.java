package uk.gov.justice.json.generator.value;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.validator.routines.RegexValidator;
import org.junit.Test;


public class HostNameGeneratorTest {

    @Test
    public void shouldGenerateHostName(){
        final HostNameGenerator hostNameGenerator = new HostNameGenerator();
        final String hostName = hostNameGenerator.next();
        final RegexValidator validator =new  RegexValidator(HostNameGenerator.pattern);
        assertThat(true, is(validator.isValid(hostName)));
    }

}