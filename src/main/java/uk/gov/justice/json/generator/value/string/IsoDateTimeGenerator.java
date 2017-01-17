package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.regex.Pattern;


public class IsoDateTimeGenerator extends Generator<String> {

    private static final String REGEX_ISO = "(19|20)[0-9][0-9]-(0[0-9]|1[0-2])-(0[1-9]|([012][0-9]|3[01])T([012][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.([0-9][0-9])([Z]))";

    public static final Pattern REGEX_PATTERN_REGEX = Pattern.compile(REGEX_ISO);

    @Override
    public String next(){
        return new RegexGenerator(REGEX_PATTERN_REGEX).next();
    }
}
