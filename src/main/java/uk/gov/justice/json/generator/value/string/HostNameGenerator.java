package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;

public class HostNameGenerator extends Generator<String> {

    public static final String  PATTERN ="[a-z]{5,25}+\\.xyz\\.com";
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile(PATTERN);

    @Override
    public String next() {
        return new RegexGenerator(HOSTNAME_PATTERN).next();
    }
}
