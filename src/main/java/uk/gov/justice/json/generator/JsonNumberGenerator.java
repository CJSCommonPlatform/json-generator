package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.value.BigDecimalGenerator;
import uk.gov.justice.json.generator.value.IntegerGenerator;
import uk.gov.justice.json.generator.value.NumberGenerator;

import java.math.BigDecimal;

import javax.json.JsonNumber;

import org.everit.json.schema.NumberSchema;

public class JsonNumberGenerator implements JsonValueGenerator<JsonNumber> {

    protected NumberGenerator numberGenerator;
    protected NumberSchema numberSchema;

    public JsonNumberGenerator(final NumberSchema numberSchema) {
        this.numberSchema = numberSchema;
        if (numberSchema.requiresInteger()) {
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
        return constructJsonNumber(numberGenerator.next());
    }

    private JsonNumber constructJsonNumber(final Number number) {
        if (numberSchema.requiresInteger()) {
            return createObjectBuilder().add(PROPERTY_NAME, (int) number).build().getJsonNumber(PROPERTY_NAME);
        } else {
            return createObjectBuilder().add(PROPERTY_NAME, (BigDecimal) number).build().getJsonNumber(PROPERTY_NAME);
        }
    }
}
