package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;

public class EmailGenerator extends Generator<String> {

    private static final String  REGEX ="[a-z]{5,25}+\\@xyz\\.com";

    public static final Pattern EMAIL_PATTERN_REGEX = Pattern.compile(REGEX);

    @Override
    public String next(){
        return new RegexGenerator(EMAIL_PATTERN_REGEX).next();
    }
}
