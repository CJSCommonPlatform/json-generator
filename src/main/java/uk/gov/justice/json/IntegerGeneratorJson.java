package uk.gov.justice.json;

import static java.lang.String.format;

import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;

import java.util.Random;

public class IntegerGeneratorJson implements JsonPropertyGenerator {

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
    IntegerGeneratorJson() {
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
    }

    /**
     * Package Access only
     * 
     * @param min value included
     * @param max value excluded
     */
    IntegerGeneratorJson(final int min, final int max) {
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
    public String nextJson() {
        return "" + random.ints(min, max).limit(1).findFirst().getAsInt();
    }
}
