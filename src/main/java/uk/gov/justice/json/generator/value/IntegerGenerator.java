package uk.gov.justice.json.generator.value;

import java.util.Random;

public class IntegerGenerator implements NumberGenerator<Integer> {
    private static final int DEFAULT_MIN = Integer.MIN_VALUE;
    private static final int DEFAULT_MAX = Integer.MAX_VALUE;
    private Integer minimum;
    private Integer maximum;
    private int multipleOf;
    private final boolean exclusiveMinimum;
    private final boolean exclusiveMaximum;

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

    private final Random random = new Random();

    public Integer next(){

        if (exclusiveMinimum){
            this.minimum  = minimum+1;
        }
        if (exclusiveMaximum){
            this.maximum =maximum-1;
        }
        int value = (random.ints(minimum,maximum).findFirst().getAsInt()/multipleOf) * multipleOf;
        return  value ;
    }

    public static class Builder {
        private int minimum;
        private int maximum;
        private int multipleOf;
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;

        public Builder() {
            this.minimum = DEFAULT_MIN;
            this.maximum = DEFAULT_MAX;
            this.multipleOf = 1;
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
