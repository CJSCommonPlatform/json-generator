package uk.gov.justice.json.generator.value.number;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.util.Optional;
import java.util.Random;

public class IntegerGenerator extends Generator<Integer> {
    private static final Integer DEFAULT_MIN = Integer.MIN_VALUE;
    private static final Integer DEFAULT_MAX = Integer.MAX_VALUE;
    private final Random random = new Random();
    private boolean exclusiveMinimum;
    private boolean exclusiveMaximum;
    private Optional<Integer> minimum;
    private Optional<Integer> maximum;
    private Optional<Integer> multipleOf;

    private IntegerGenerator(final Builder builder) {
        this.minimum = java.util.Optional.of(DEFAULT_MIN);
        this.maximum = Optional.of(DEFAULT_MAX);
        this.multipleOf =Optional.empty();
        if (builder.minimum.isPresent()) {
            this.minimum = Optional.of(builder.minimum.get());
        }
        if (builder.maximum.isPresent()) {
            this.maximum = Optional.of(builder.maximum.get());
        }
        if (builder.multipleOf.isPresent()) {
            this.multipleOf =  Optional.of(builder.multipleOf.get());
        }
        if (builder.exclusiveMinimum) {
            this.minimum = Optional.of(builder.minimum.get() + 1);
            this.exclusiveMinimum = builder.exclusiveMinimum;
        }
        if (builder.exclusiveMaximum) {
            this.maximum = Optional.of(builder.maximum.get() - 1);
            this.exclusiveMaximum = builder.exclusiveMaximum;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer next() {
        int minimum = this.minimum.get();
        int maximum = this.maximum.get();
        if (this.multipleOf.isPresent()) {
            return ((random.ints(minimum, maximum).findFirst().getAsInt()) / multipleOf.get()) * multipleOf.get();
        }else{
            return random.ints(minimum, maximum).findFirst().getAsInt();
        }
    }

    public static class Builder {
        private Optional<Integer> minimum = Optional.empty();
        private Optional<Integer> maximum = Optional.empty();
        private Optional<Integer> multipleOf=Optional.empty();
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;

        public Builder() {
        }

        public IntegerGenerator build() {
            return new IntegerGenerator(this);
        }

        public Builder exclusiveMaximum(boolean exclusiveMaximum) {
            this.exclusiveMaximum = exclusiveMaximum;
            return this;
        }

        public Builder exclusiveMinimum(boolean exclusiveMinimum) {
            this.exclusiveMinimum = exclusiveMinimum;
            return this;
        }

        public Builder maximum(Optional<Integer> maximum) {
            this.maximum = maximum;
            return this;
        }

        public Builder minimum(Optional<Integer> minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder multipleOf(Optional<Integer> multipleOf) {
            this.multipleOf = multipleOf;
            return this;
        }
    }
}
