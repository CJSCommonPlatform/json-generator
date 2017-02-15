package uk.gov.justice.json.generator.value.number;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.math.BigDecimal;
import java.util.Optional;

public class BigDecimalGenerator extends Generator<BigDecimal> {

    private static final long DEFAULT_MIN = -1000000000L;
    private static final long DEFAULT_MAX = 1000000000L;
    private Optional<BigDecimal> minimum = Optional.of(BigDecimal.valueOf(DEFAULT_MIN));
    private Optional<BigDecimal> maximum = Optional.of(BigDecimal.valueOf(DEFAULT_MAX));
    private Optional<BigDecimal> multipleOf;
    private boolean exclusiveMinimum;
    private boolean exclusiveMaximum;
    private int scale = 1;

    private BigDecimalGenerator(final Builder builder) {
        this.multipleOf = Optional.empty();
        if (builder.minimum.isPresent()) {
            this.minimum = builder.minimum;
        }
        if (builder.maximum.isPresent()) {
            this.maximum = builder.maximum;
        }
        if (builder.multipleOf.isPresent()) {
            this.multipleOf = builder.multipleOf;
        }
        if (builder.exclusiveMinimum) {
            this.minimum = Optional.of(builder.minimum.get().add(BigDecimal.ONE));
            this.exclusiveMinimum = builder.exclusiveMinimum;
        }
        if (builder.exclusiveMaximum) {
            this.maximum = Optional.of(builder.maximum.get().subtract(BigDecimal.ONE));
            this.exclusiveMaximum = builder.exclusiveMaximum;
        }
        this.scale  = builder.scale;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public BigDecimal next() {
      if (this.multipleOf.isPresent()){
          final BigDecimal minimumReduced = minimum.get().divide(multipleOf.get());
          final BigDecimal maximumReduced = maximum.get().divide(multipleOf.get());
          final BigDecimal randomBigDecimal = minimumReduced
                    .add(new BigDecimal(Math.random())
                            .multiply(maximumReduced
                                    .subtract(minimumReduced)))
                    .setScale(0, BigDecimal.ROUND_HALF_UP)
                    .multiply(multipleOf.get()).setScale(scale);
            return randomBigDecimal;
        }else{
            return this.minimum.get()
                    .add(new BigDecimal(Math.random())
                            .multiply(this.maximum.get())
                                    .subtract(this.minimum.get()))
                    .setScale(0, BigDecimal.ROUND_HALF_UP).setScale(scale);
        }

    }

    public static class Builder {
        private Optional<BigDecimal> minimum = Optional.empty();
        private Optional<BigDecimal> maximum = Optional.empty();;
        private Optional<BigDecimal> multipleOf = Optional.empty();
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;
        private int scale;

        public Builder() {

        }

        public BigDecimalGenerator build() {
            return new BigDecimalGenerator(this);
        }

        public Builder exclusiveMaximum(boolean exclusiveMaximum) {
            this.exclusiveMaximum = exclusiveMaximum;
            return this;
        }

        public Builder exclusiveMinimum(boolean exclusiveMinimum) {
            this.exclusiveMinimum = exclusiveMinimum;
            return this;
        }

        public Builder maximum(Optional<BigDecimal> maximum) {
            this.maximum = maximum;
            return this;
        }

        public Builder minimum(Optional<BigDecimal> minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder multipleOf(Optional<BigDecimal> multipleOf) {
            this.multipleOf = multipleOf;
            return this;
        }

        public Builder scale(int scale) {
            this.scale = scale;
            return this;
        }
    }
}
