package uk.gov.justice.json.generation.generators.values;

import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import java.util.regex.Pattern;

import com.mifmif.common.regex.Generex;

public class RegexValueGenerator implements JsonValueGenerator {

    private final Pattern pattern;

    public RegexValueGenerator(final Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String nextValue() {

        //TODO fix the PATTERN properly
        // final String fixedPattern = PATTERN.replace('^', ' ').replace('$', ' ').trim();
        return DOUBLE_QUOTE + new Generex(pattern.toString()).random() + DOUBLE_QUOTE;
    }
}
