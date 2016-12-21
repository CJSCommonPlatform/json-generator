package uk.gov.justice.json.generators.values;

import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import com.mifmif.common.regex.Generex;

public class RegexValueGenerator implements JsonValueGenerator {

    private final String pattern;

    public RegexValueGenerator(final String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String nextValue() {

        //TODO fix the pattern properly
        final String fixedPattern = pattern.replace('^', ' ').replace('$', ' ').trim();
        return DOUBLE_QUOTE + new Generex(fixedPattern).random() + DOUBLE_QUOTE;
    }
}
