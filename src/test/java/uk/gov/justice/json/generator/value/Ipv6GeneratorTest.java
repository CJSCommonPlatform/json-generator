package uk.gov.justice.json.generator.value;

import static org.junit.Assert.fail;

import uk.gov.justice.json.generator.value.string.Ipv6Generator;

import com.google.common.net.InetAddresses;
import org.junit.Test;


public class Ipv6GeneratorTest {

    @Test
    public void shouldGenerateIpv6() {
        final Ipv6Generator ipv6Generator = new Ipv6Generator();
        final String ipAddress = ipv6Generator.next();
        try {
            InetAddresses.forString(ipAddress);
        } catch (Exception e) {
            fail();
        }
    }
}
