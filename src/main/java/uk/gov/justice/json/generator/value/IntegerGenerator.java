package uk.gov.justice.json.generator.value;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;

import java.util.Random;

public class IntegerGenerator implements NumberGenerator<Integer> {

      private final Random random = new Random();

        private final int min;
        private final int max;

    public IntegerGenerator() {
            this(0, MAX_VALUE);
        }

    public IntegerGenerator(final int min, final int max) {
            if (min >= max) {
                throw new IllegalArgumentException(
                        format("Min value cannot be greater than or equal to Max value, got Min: %s and Max: %s", min, max));
            }
            this.min = min;
            this.max = max;
        }

    public Integer nextValue(){
        return random.ints(min, max).limit(1).findFirst().getAsInt();
    }
}
