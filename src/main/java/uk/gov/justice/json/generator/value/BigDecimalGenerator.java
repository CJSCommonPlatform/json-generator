package uk.gov.justice.json.generator.value;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.Random;

public class BigDecimalGenerator implements NumberGenerator<BigDecimal>{

    private uk.gov.justice.services.test.utils.core.random.BigDecimalGenerator bigDecimalGeneratorUtil = new uk.gov.justice.services.test.utils.core.random.BigDecimalGenerator();

    public BigDecimal next() {
        return bigDecimalGeneratorUtil.next();
    }
    private static final long DEFAULT_MIN = -1000000000L;
    private static final long DEFAULT_MAX = 1000000000L;
    private final BigDecimal minimum;
    private final BigDecimal maximum;
    private final BigDecimal multipleOf;
    private final boolean exclusiveMinimum;
    private final boolean exclusiveMaximum;
    private final Integer scale;



    public static BigDecimalGenerator.Builder builder() {
        return new BigDecimalGenerator.Builder();
    }

    private BigDecimalGenerator() {
        this(builder());
    }


    private BigDecimalGenerator(BigDecimalGenerator.Builder builder) {
        this.minimum = builder.minimum;
        this.maximum = builder.maximum;
        this.exclusiveMinimum = builder.exclusiveMinimum;
        this.exclusiveMaximum = builder.exclusiveMaximum;
        this.multipleOf = builder.multipleOf;
        this.scale = builder.scale;
    }


    private void checkMinAndMaxValues(){
        if (minimum.doubleValue() >= maximum.doubleValue()) {
            throw new IllegalArgumentException(
                    format("Min value cannot be greater than or equal to Max value, got Min: %s and Max: %s", minimum, maximum));
        }
    }

    private void checkDefaultMinValue(){
        if (minimum.doubleValue() < DEFAULT_MIN) {
            throw new IllegalArgumentException(
                    format("Min value cannot be less than what is allowed for the type Integer, got Min: %s ", minimum));
        }
    }

    private void checkDefaultMaxValue(){
        if (maximum.doubleValue()> DEFAULT_MAX) {
            throw new IllegalArgumentException(
                    format("Max value cannot be less than what is allowed for the type Integer, got Max: %s ", maximum));
        }
    }

    private void checkScale(){
           if (scale < 0) {
                throw new IllegalArgumentException(format("Scale cannot be less than zero, got %s", scale));
            }
    }

    private void checkMaximum(int subject) {
        if(this.maximum != null) {
            if(this.exclusiveMaximum && this.maximum.doubleValue() <= subject) {
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

    public static class Builder {
        private BigDecimal minimum;
        private BigDecimal maximum;
        private BigDecimal multipleOf;
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;
        private Integer scale;

        public Builder() {
        }

        public BigDecimalGenerator build() {
            return new BigDecimalGenerator(this);
        }

        public BigDecimalGenerator.Builder exclusiveMaximum(boolean exclusiveMaximum) {
            this.exclusiveMaximum = exclusiveMaximum;
            return this;
        }

        public BigDecimalGenerator.Builder exclusiveMinimum(boolean exclusiveMinimum) {
            this.exclusiveMinimum = exclusiveMinimum;
            return this;
        }

        public BigDecimalGenerator.Builder maximum(BigDecimal maximum) {
            this.maximum = maximum;
            return this;
        }

        public BigDecimalGenerator.Builder minimum(BigDecimal minimum) {
            this.minimum = minimum;
            return this;
        }

        public BigDecimalGenerator.Builder multipleOf(BigDecimal multipleOf) {
            this.multipleOf = multipleOf;
            return this;
        }

        public BigDecimalGenerator.Builder scale(Integer scale) {
            this.scale = scale;
            return this;
        }
    }
}
