package uk.gov.justice.json.generator.value;

import java.util.regex.Pattern;

import com.mifmif.common.regex.Generex;

public class RegexGenerator implements StringGenerator {

    private final Pattern pattern;

    public RegexGenerator(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String next() {
        return new Generex(pattern.toString()).random();
    }
}
