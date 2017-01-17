package uk.gov.justice.json.generator.value;

import static org.junit.Assert.fail;

import uk.gov.justice.json.generator.value.string.Ipv4Generator;

import com.google.common.net.InetAddresses;
import org.junit.Test;


public class Ipv4GeneratorTest {

    @Test
    public void shouldGenerateIpv4()  {
        final Ipv4Generator ipv4Generator = new Ipv4Generator();
        final String ipAddress = ipv4Generator.next();
        try{
            InetAddresses.forString(ipAddress);
        }catch (Exception e){
            fail();
        }
    }
}
