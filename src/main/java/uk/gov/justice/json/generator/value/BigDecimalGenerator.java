package uk.gov.justice.json.generator.value;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

public class BigDecimalGenerator implements NumberGenerator<BigDecimal> {

    private static final long DEFAULT_MIN = -1000000000L;
    private static final long DEFAULT_MAX = 1000000000L;
    private Optional<BigDecimal> minimum = Optional.of(BigDecimal.valueOf(DEFAULT_MIN));
    private Optional<BigDecimal> maximum = Optional.of(BigDecimal.valueOf(DEFAULT_MAX));
    private Optional<BigDecimal> multipleOf;
    private boolean exclusiveMinimum;
    private boolean exclusiveMaximum;
    private int scale =1;
    private final Random random = new Random();

    private BigDecimalGenerator(BigDecimalGenerator.Builder builder) {
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

    public static BigDecimalGenerator.Builder builder() {
        return new BigDecimalGenerator.Builder();
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

        public BigDecimalGenerator.Builder exclusiveMaximum(boolean exclusiveMaximum) {
            this.exclusiveMaximum = exclusiveMaximum;
            return this;
        }

        public BigDecimalGenerator.Builder exclusiveMinimum(boolean exclusiveMinimum) {
            this.exclusiveMinimum = exclusiveMinimum;
            return this;
        }

        public BigDecimalGenerator.Builder maximum(Optional<BigDecimal> maximum) {
            this.maximum = maximum;
            return this;
        }

        public BigDecimalGenerator.Builder minimum(Optional<BigDecimal> minimum) {
            this.minimum = minimum;
            return this;
        }

        public BigDecimalGenerator.Builder multipleOf(Optional<BigDecimal> multipleOf) {
            this.multipleOf = multipleOf;
            return this;
        }

        public BigDecimalGenerator.Builder scale(int scale) {
            this.scale = scale;
            return this;
        }
    }
}
