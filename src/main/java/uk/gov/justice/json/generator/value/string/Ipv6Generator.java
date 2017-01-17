package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.regex.Pattern;

import com.google.common.net.InetAddresses;

public class Ipv6Generator extends Generator<String> {
    @Override
    public java.lang.String next() {
        final int IPV6_BYTES = 16;
        final byte[] tmp2 = new byte[IPV6_BYTES];
        RANDOM.nextBytes(tmp2);
        try {
            return Inet6Address.getByAddress(tmp2).getHostAddress();
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(String.format(
                    "Host address '%s' is not a valid IPv6 address.", Arrays.toString(tmp2)), e);
        }
    }
}
