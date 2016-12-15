package uk.gov.justice.json;

import static java.lang.String.format;

import java.util.Random;

public class IntegerGenerator implements PropertyGenerator {

    private final Random random = new Random();

    /**
     * The minimum value of the range
     */
    private final int min;

    /**
     * The maximum value of the range
     */
    private final int max;

    /**
     * Package Access only
     * 
     */
    IntegerGenerator() {
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
    }

    /**
     * Package Access only
     * 
     * @param min value included
     * @param max value excluded
     */
    IntegerGenerator(final int min, final int max) {
        if (min >= max) {
            throw new IllegalArgumentException(
                            format("Min value cannot be greater than or equal to Max value, got Min: %s and Max: %s", min, max));
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String next() {
        return "" + random.ints(min, max).limit(1).findFirst().getAsInt();
    }
}
