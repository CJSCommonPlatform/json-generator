package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;

import com.google.common.net.InetAddresses;

public class Ipv4Generator extends Generator<String> {

    @Override
    public java.lang.String next() {
       return InetAddresses.fromInteger(RANDOM.nextInt()).getHostAddress();
    }
}
