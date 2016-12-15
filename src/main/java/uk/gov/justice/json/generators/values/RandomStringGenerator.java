package uk.gov.justice.json.generators.values;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RandomStringGenerator {

    private static final int STRING_LENGTH = 10;

    public String randomString() {
        return randomAlphanumeric(STRING_LENGTH);
    }
}
