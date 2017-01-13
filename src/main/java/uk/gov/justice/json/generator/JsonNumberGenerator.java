package uk.gov.justice.json.generator;

import static uk.gov.justice.json.generator.JsonValueGenerators.buildJsonNumber;

import uk.gov.justice.json.generator.value.BigDecimalGenerator;
import uk.gov.justice.json.generator.value.IntegerGenerator;
import uk.gov.justice.json.generator.value.NumberGenerator;

import java.math.BigDecimal;

import javax.json.JsonNumber;

import org.everit.json.schema.NumberSchema;

public class JsonNumberGenerator extends JsonValueGenerator<JsonNumber> {

    protected NumberGenerator numberGenerator;

    private boolean requiresInteger;

    public JsonNumberGenerator(final NumberSchema numberSchema) {

        requiresInteger = numberSchema.requiresInteger();

        if (requiresInteger) {
            numberGenerator = integerGenerator(numberSchema);
        } else {
            numberGenerator = bigDecimalGenerator(numberSchema);
        }
    }

    private IntegerGenerator integerGenerator(final NumberSchema numberSchema) {
        IntegerGenerator.Builder builder = IntegerGenerator.builder();
        if (numberSchema.getMinimum() != null) {
            builder.minimum(numberSchema.getMinimum().intValue());
        }
        if (numberSchema.getMaximum() != null) {
            builder.maximum(numberSchema.getMaximum().intValue());
        }
        if (numberSchema.getMultipleOf() != null) {
            builder.maximum(numberSchema.getMaximum().intValue());
        }
        if (numberSchema.isExclusiveMaximum()) {
            builder.exclusiveMaximum(true);
        }
        if (numberSchema.isExclusiveMinimum()){
            builder.exclusiveMinimum(true);
        }
        return builder.build();
    }

    private BigDecimalGenerator bigDecimalGenerator(final NumberSchema numberSchema) {
        BigDecimalGenerator.Builder builder = BigDecimalGenerator.builder();
        if (numberSchema.getMinimum() != null) {
            builder.minimum(new BigDecimal(numberSchema.getMinimum().toString()));
        }
        if (numberSchema.getMaximum() != null) {
            builder.maximum(new BigDecimal(numberSchema.getMaximum().toString()));
        }
        if (numberSchema.getMultipleOf() != null) {
            builder.maximum(new BigDecimal(numberSchema.getMultipleOf().toString()));
        }
        if (numberSchema.isExclusiveMaximum()) {
            builder.exclusiveMaximum(true);
        }
        if (numberSchema.isExclusiveMinimum()){
            builder.exclusiveMinimum(true);
        }
        return builder.build();
    }

    public JsonNumber next() {
        final Number number = numberGenerator.next();
        if (requiresInteger) {
            return buildJsonNumber((Integer) number);
        } else {
            return buildJsonNumber((BigDecimal) number);
        }
    }
}
