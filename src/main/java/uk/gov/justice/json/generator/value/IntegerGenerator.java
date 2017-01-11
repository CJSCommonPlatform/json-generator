package uk.gov.justice.json.generator.value;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.Random;

import org.everit.json.schema.NumberSchema;
import org.everit.json.schema.ValidationException;

//TODO: range(minimum, exclusive minimum, maximum, exclusive maximum)
//TODO: multipleOf
public class IntegerGenerator implements NumberGenerator<Integer> {

    private final Integer minimum;
    private final Number maximum;
    private final Number multipleOf;
    private final boolean exclusiveMinimum;
    private final boolean exclusiveMaximum;

    public static NumberSchema.Builder builder() {
        return new NumberSchema.Builder();
    }

    public IntegerGenerator() {
        this(builder());
    }

    public IntegerGenerator(IntegerGenerator.Builder builder) {
        super(builder);
        this.minimum = builder.minimum;
        this.maximum = builder.maximum;
        this.exclusiveMinimum = builder.exclusiveMinimum;
        this.exclusiveMaximum = builder.exclusiveMaximum;
        this.multipleOf = builder.multipleOf;
    }

    private void checkMaximum(double subject) {
        if(this.maximum != null) {
            if(this.exclusiveMaximum && this.maximum.doubleValue() <= subject) {
                throw new ValidationException(this, subject + " is not lower than " + this.maximum, "exclusiveMaximum");
            }

            if(this.maximum.doubleValue() < subject) {
                throw new ValidationException(this, subject + " is not lower or equal to " + this.maximum, "maximum");
            }
        }

    }

    private void checkMinimum(double subject) {
        if(this.minimum != null) {
            if(this.exclusiveMinimum && subject <= this.minimum.doubleValue()) {
                throw new ValidationException(this, subject + " is not higher than " + this.minimum, "exclusiveMinimum");
            }

            if(subject < this.minimum.doubleValue()) {
                throw new ValidationException(this, subject + " is not higher or equal to " + this.minimum, "minimum");
            }
        }

    }

    private void checkMultipleOf(double subject) {
        if(this.multipleOf != null) {
            BigDecimal remainder = BigDecimal.valueOf(subject).remainder(BigDecimal.valueOf(this.multipleOf.doubleValue()));
            if(remainder.compareTo(BigDecimal.ZERO) != 0) {
                throw new ValidationException(this, subject + " is not a multiple of " + this.multipleOf, "multipleOf");
            }
        }

    }

    private static final double DEFAULT_MIN = Integer.MIN_VALUE;
    private static final double DEFAULT_MAX = Integer.MAX_VALUE;

    private final Random random = new Random();

    private final int min;
    private final int max;

    public IntegerGenerator() {
            this(MIN_VALUE, MAX_VALUE);
    }

    public IntegerGenerator(final int min, final int max) {
            if (min >= max) {
                throw new IllegalArgumentException(
                        format("Min value cannot be greater than or equal to Max value, got Min: %s and Max: %s", min, max));
            }
            this.min = min;
            this.max = max;
        }

    public Integer next(){
        return random.ints(min, max).limit(1).findFirst().getAsInt();
    }

    private void validateMinimum(){

    }

    public static class Builder extends org.everit.json.schema.Schema.Builder<NumberSchema> {
        private Number minimum;
        private Number maximum;
        private Number multipleOf;
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;

        public Builder() {
        }

        public NumberSchema build() {
            return new NumberSchema(this);
        }

        public NumberSchema.Builder exclusiveMaximum(boolean exclusiveMaximum) {
            this.exclusiveMaximum = exclusiveMaximum;
            return this;
        }

        public NumberSchema.Builder exclusiveMinimum(boolean exclusiveMinimum) {
            this.exclusiveMinimum = exclusiveMinimum;
            return this;
        }

        public NumberSchema.Builder maximum(Number maximum) {
            this.maximum = maximum;
            return this;
        }

        public NumberSchema.Builder minimum(Number minimum) {
            this.minimum = minimum;
            return this;
        }

        public NumberSchema.Builder multipleOf(Number multipleOf) {
            this.multipleOf = multipleOf;
            return this;
        }

        public NumberSchema.Builder requiresInteger(boolean requiresInteger) {
            this.requiresInteger = requiresInteger;
            return this;
        }

        public NumberSchema.Builder requiresNumber(boolean requiresNumber) {
            this.requiresNumber = requiresNumber;
            return this;
        }

        public  IntegerGenerator build(){

        }
    }

}
