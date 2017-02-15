package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

import org.apache.commons.lang3.RandomStringUtils;

public class SimpleStringGenerator extends Generator<String> {

    private final int minLength;
    private final int maxLength;

    public SimpleStringGenerator(final int minLength, final int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public String next() {
        if (maxLength != 0) {
            return RandomStringUtils.randomAlphanumeric(maxLength).substring(maxLength - minLength);
        } else {
            return  RandomStringUtils.randomAlphanumeric(minLength);
        }
    }
}
