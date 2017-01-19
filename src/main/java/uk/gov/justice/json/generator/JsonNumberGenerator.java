package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonNumber;

import uk.gov.justice.json.generator.value.number.BigDecimalGenerator;
import uk.gov.justice.json.generator.value.number.IntegerGenerator;
import uk.gov.justice.services.test.utils.core.random.Generator;

import java.math.BigDecimal;
import java.util.Optional;

import javax.json.JsonNumber;
import javax.json.JsonValue;

import org.everit.json.schema.NumberSchema;

public class JsonNumberGenerator extends Generator<JsonValue> {

    private Generator<? extends Number> numberGenerator;

    private boolean requiresInteger;

    public JsonNumberGenerator(final NumberSchema numberSchema) {

        requiresInteger = numberSchema.requiresInteger();

        if (requiresInteger) {
            numberGenerator = integerGenerator(numberSchema);
        } else {
            numberGenerator = bigDecimalGenerator(numberSchema);
        }
    }

    private Generator<Integer> integerGenerator(final NumberSchema numberSchema) {
        IntegerGenerator.Builder builder = IntegerGenerator.builder();
        if (numberSchema.getMinimum() != null) {
            builder.minimum(Optional.of(numberSchema.getMinimum().intValue()));
        }
        if (numberSchema.getMaximum() != null) {
            builder.maximum(Optional.of(numberSchema.getMaximum().intValue()));
        }
        if (numberSchema.getMultipleOf() != null) {
            builder.maximum(Optional.of(numberSchema.getMaximum().intValue()));
        }
        if (numberSchema.isExclusiveMaximum()) {
            builder.exclusiveMaximum(true);
        }
        if (numberSchema.isExclusiveMinimum()){
            builder.exclusiveMinimum(true);
        }
        return builder.build();
    }

    private Generator<BigDecimal> bigDecimalGenerator(final NumberSchema numberSchema) {
        BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        if (numberSchema.getMinimum() != null) {
            builder.minimum(Optional.of(new BigDecimal(numberSchema.getMinimum().toString())));
        }
        if (numberSchema.getMaximum() != null) {
            builder.maximum(Optional.of(new BigDecimal(numberSchema.getMaximum().toString())));
        }
        if (numberSchema.getMultipleOf() != null) {
            builder.maximum(Optional.of(new BigDecimal(numberSchema.getMultipleOf().toString())));
        }
        if (numberSchema.isExclusiveMaximum()) {
            builder.exclusiveMaximum(true);
        }
        if (numberSchema.isExclusiveMinimum()){
            builder.exclusiveMinimum(true);
        }
        return builder.build();
    }

    @Override
    public JsonNumber next() {
        final Number number = numberGenerator.next();
        if (requiresInteger) {
            return buildJsonNumber((Integer) number);
        } else {
            return buildJsonNumber((BigDecimal) number);
        }
    }
}
