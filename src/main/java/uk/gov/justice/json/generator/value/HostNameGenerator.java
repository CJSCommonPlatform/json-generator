package uk.gov.justice.json.generator.value;

import java.util.regex.Pattern;

public class HostNameGenerator implements StringGenerator{

    public static final String pattern = "[a-z0-9]+[\\.]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+";

    @Override
    public String next() {
        return new RegexGenerator(Pattern.compile(pattern)).next();
    }
}
