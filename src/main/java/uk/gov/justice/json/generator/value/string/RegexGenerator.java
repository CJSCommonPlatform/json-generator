package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;

import com.mifmif.common.regex.Generex;

public class RegexGenerator extends Generator<String> {

    private final Generex generex;

    public RegexGenerator(final Pattern pattern) {
        generex = new Generex(pattern.toString());
    }

    @Override
    public String next() {
        return generex.random();
    }
}
