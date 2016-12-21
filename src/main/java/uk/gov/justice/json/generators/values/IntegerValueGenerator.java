package uk.gov.justice.json.generators.values;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;

import java.util.Random;

public class IntegerValueGenerator implements JsonValueGenerator {

    private final Random random = new Random();

    private final int min;
    private final int max;

    public IntegerValueGenerator() {
        this(0, MAX_VALUE);
    }

    public IntegerValueGenerator(final int min, final int max) {
        if (min >= max) {
            throw new IllegalArgumentException(
                    format("Min value cannot be greater than or equal to Max value, got Min: %s and Max: %s", min, max));
        }
        this.min = min;
        this.max = max;
    }

    public String nextValue() {
        return random.ints(min, max).limit(1).findFirst().getAsInt() + "";
    }
}
