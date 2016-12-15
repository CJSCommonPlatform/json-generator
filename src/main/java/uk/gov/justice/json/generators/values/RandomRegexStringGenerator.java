package uk.gov.justice.json.generators.values;

import com.mifmif.common.regex.Generex;

public class RandomRegexStringGenerator {

    public String randomString(final String pattern) {

        //TODO fix the pattern properly
        final String fixedPattern = pattern.replace('^', ' ').replace('$', ' ').trim();
        return new Generex(fixedPattern).random();
    }
}
