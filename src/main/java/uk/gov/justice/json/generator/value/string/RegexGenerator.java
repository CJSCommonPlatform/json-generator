package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;

import com.mifmif.common.regex.Generex;

public class RegexGenerator extends Generator<String> {

    private final Pattern pattern;

    public RegexGenerator(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String next() {
        return new Generex(pattern.toString()).random();
    }
}
