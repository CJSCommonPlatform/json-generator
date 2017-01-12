package uk.gov.justice.json.generator.value;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.max;
import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.Random;
import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.ValidationException;

public class IntegerGenerator implements NumberGenerator<Integer> {
    private static final double DEFAULT_MIN = Integer.MIN_VALUE;
    private static final double DEFAULT_MAX = Integer.MAX_VALUE;
    private final Integer minimum;
    private final Integer maximum;
    private final Integer multipleOf;
    private final boolean exclusiveMinimum;
    private final boolean exclusiveMaximum;

    private IntegerGenerator() {
        this(builder());
    }

    public static IntegerGenerator.Builder builder() {
        return new IntegerGenerator.Builder();
    }

    private IntegerGenerator(IntegerGenerator.Builder builder) {
        this.minimum = builder.minimum;
        this.maximum = builder.maximum;
        this.exclusiveMinimum = builder.exclusiveMinimum;
        this.exclusiveMaximum = builder.exclusiveMaximum;
        this.multipleOf = builder.multipleOf;
    }


    private void checkMinAndMaxValues(){
        if (minimum >= maximum) {
            throw new IllegalArgumentException(
                    format("Min value cannot be greater than or equal to Max value, got Min: %s and Max: %s", minimum, maximum));
        }
    }

    private void checkDefaultMinValue(){
        if (minimum < DEFAULT_MIN) {
            throw new IllegalArgumentException(
                    format("Min value cannot be less than what is allowed for the type Integer, got Min: %s ", minimum));
        }
    }

    private void checkDefaultMaxValue(){
        if (minimum > DEFAULT_MAX) {
            throw new IllegalArgumentException(
                    format("Max value cannot be less than what is allowed for the type Integer, got Max: %s ", maximum));
        }
    }
    private void checkMaximum(int subject) {
        if(this.maximum != null) {
            if(this.exclusiveMaximum && this.maximum.intValue() <= subject) {
                throw new IllegalArgumentException(
                        format( subject + " is not lower than " + this.maximum, "exclusiveMaximum"));
            }

            if(this.maximum.doubleValue() < subject) {
                throw new IllegalArgumentException(
                        format(subject + " is not lower or equal to " + this.maximum, "maximum"));
            }
        }

    }

    private void checkMinimum(int subject) {
        if(this.minimum != null) {
            if(this.exclusiveMinimum && subject <= this.minimum.doubleValue()) {
                throw new IllegalArgumentException(
                        format( subject + " is not higher than " + this.minimum, "exclusiveMinimum"));
            }

            if(subject < this.minimum.doubleValue()) {
                throw new IllegalArgumentException(
                        format(subject + " is not higher or equal to " + this.minimum, "minimum"));
            }
        }

    }

    private void checkMultipleOf(double subject) {
        if(this.multipleOf != null) {
            BigDecimal remainder = BigDecimal.valueOf(subject).remainder(BigDecimal.valueOf(this.multipleOf.intValue()));
            if(remainder.compareTo(BigDecimal.ZERO) != 0) {
                throw new IllegalArgumentException(
                        format(subject + " is not a multiple of " + this.multipleOf, "multipleOf"));
            }
        }

    }


    private final Random random = new Random();

    public Integer next(){
        return random.ints(minimum, maximum).limit(1).findFirst().getAsInt();
    }


    public static class Builder {
        private int minimum;
        private int maximum;
        private int multipleOf;
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;

        public Builder() {
        }

        public IntegerGenerator build() {
            return new IntegerGenerator(this);
        }

        public IntegerGenerator.Builder exclusiveMaximum(boolean exclusiveMaximum) {
            this.exclusiveMaximum = exclusiveMaximum;
            return this;
        }

        public IntegerGenerator.Builder exclusiveMinimum(boolean exclusiveMinimum) {
            this.exclusiveMinimum = exclusiveMinimum;
            return this;
        }

        public IntegerGenerator.Builder maximum(int maximum) {
            this.maximum = maximum;
            return this;
        }

        public IntegerGenerator.Builder minimum(int minimum) {
            this.minimum = minimum;
            return this;
        }

        public IntegerGenerator.Builder multipleOf(int multipleOf) {
            this.multipleOf = multipleOf;
            return this;
        }
    }

}
