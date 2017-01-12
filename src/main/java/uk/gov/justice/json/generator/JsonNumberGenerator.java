package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.value.BigDecimalGenerator;
import uk.gov.justice.json.generator.value.IntegerGenerator;
import uk.gov.justice.json.generator.value.NumberGenerator;

import java.math.BigDecimal;

import javax.json.JsonNumber;

import org.everit.json.schema.NumberSchema;

public class JsonNumberGenerator implements JsonValueGenerator<JsonNumber>{

    private NumberGenerator numberGenerator;
    private boolean isInteger;

    public JsonNumberGenerator(final NumberSchema numberSchema) {
        if (numberSchema.requiresInteger()){
            numberGenerator =  IntegerGenerator.builder().build();
            this.isInteger=true;
        }else {
            numberGenerator = BigDecimalGenerator.builder().build();
        }
    }

    @Override
    public JsonNumber next() {
        return constructJsonNumber(numberGenerator.next());
    }

    private JsonNumber constructJsonNumber(final Number number) {
        if (isInteger) {
            return createObjectBuilder().add(PROPERTY_NAME, (int) number).build().getJsonNumber(PROPERTY_NAME);
        }else{
            return createObjectBuilder().add(PROPERTY_NAME, (BigDecimal) number).build().getJsonNumber(PROPERTY_NAME);
        }
    }
}
