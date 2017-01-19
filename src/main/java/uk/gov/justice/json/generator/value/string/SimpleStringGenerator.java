package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;
import uk.gov.justice.services.test.utils.core.random.StringGenerator;

import org.apache.commons.lang3.RandomStringUtils;

public class SimpleStringGenerator extends Generator<String> {

    private int minLength =10;
    private final int maxLength;

    public SimpleStringGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public String next() {
        if (maxLength != 0) {
            return RandomStringUtils.randomAlphanumeric(maxLength).substring(maxLength - minLength);
        }else{
            return  RandomStringUtils.randomAlphanumeric(minLength);
        }
    }
}
