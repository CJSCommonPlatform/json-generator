package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;

public class Ipv4Generator extends Generator<String> {

    public static final String PATTERN = "((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])";

    private static final Pattern IPV4_PATTERN = Pattern.compile(PATTERN);
    @Override
    public java.lang.String next() {
        return new RegexGenerator(IPV4_PATTERN).next();
    }
}
