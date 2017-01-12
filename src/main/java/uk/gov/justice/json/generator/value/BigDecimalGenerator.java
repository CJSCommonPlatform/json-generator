package uk.gov.justice.json.generator.value;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class BigDecimalGenerator implements NumberGenerator<BigDecimal>{

    private static final long DEFAULT_MIN = -1000000000L;
    private static final long DEFAULT_MAX = 1000000000L;
    private final BigDecimal minimum;
    private final BigDecimal maximum;
    private final BigDecimal multipleOf;
    private final boolean exclusiveMinimum;
    private final boolean exclusiveMaximum;
    private final int scale;

    public static BigDecimalGenerator.Builder builder() {
        return new BigDecimalGenerator.Builder();
    }

    private final Random random = new Random();

    @Override
    public BigDecimal next(){

        if (exclusiveMinimum){
            this.minimum.add(BigDecimal.ONE);
        }
        if (exclusiveMaximum){
            this.maximum.subtract(BigDecimal.ONE);
        }
        return (generateRandomBigDecimalForRange(minimum,maximum,scale)
               // .divide(multipleOf,scale, RoundingMode.DOWN))
                .multiply(multipleOf));
    }

    private BigDecimal generateRandomBigDecimalForRange(final BigDecimal min, final BigDecimal max,final int scale) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(scale,BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimalGenerator(BigDecimalGenerator.Builder builder) {
        this.minimum = builder.minimum;
        this.maximum = builder.maximum;
        this.exclusiveMinimum = builder.exclusiveMinimum;
        this.exclusiveMaximum = builder.exclusiveMaximum;
        this.multipleOf = builder.multipleOf;
        this.scale = builder.scale;
    }

    public static class Builder {
        private BigDecimal minimum;
        private BigDecimal maximum;
        private BigDecimal multipleOf;
        private boolean exclusiveMinimum = false;
        private boolean exclusiveMaximum = false;
        private int scale;

        public Builder() {
            this.minimum = new BigDecimal(DEFAULT_MIN);
            this.maximum = new BigDecimal(DEFAULT_MAX);
            this.multipleOf = BigDecimal.ONE;
            this.scale = 1;
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

        public BigDecimalGenerator.Builder scale(int scale) {
            this.scale = scale;
            return this;
        }
    }
}
