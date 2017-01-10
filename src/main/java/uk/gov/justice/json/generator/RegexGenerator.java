package uk.gov.justice.json.generator;

import java.util.regex.Pattern;

/**
 * Created by david on 10/01/17.
 */
public class RegexGenerator implements StringGenerator {
    
    public RegexGenerator(Pattern pattern) {
    }

    @Override
    public String nextValue() {
        return null;
    }
}
