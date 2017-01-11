package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.value.BigDecimalGenerator;
import uk.gov.justice.json.generator.value.IntegerGenerator;
import uk.gov.justice.json.generator.value.NumberGenerator;

import java.math.BigDecimal;

import javax.json.JsonNumber;

import org.everit.json.schema.NumberSchema;

public class JsonNumberGenerator implements JsonValueGenerator<JsonNumber>{

    private final String propertyName;
    private NumberGenerator numberGenerator;
    private boolean isInteger;

    public JsonNumberGenerator(final String propertyName,final NumberSchema numberSchema) {
        this.propertyName =propertyName;
        if (numberSchema.requiresInteger()){
            numberGenerator = new IntegerGenerator();
            this.isInteger=true;
        }else {
            numberGenerator = new BigDecimalGenerator();
        }
    }

    @Override
    public JsonNumber next() {
        return constructJsonNumber(numberGenerator.next());
    }

    private JsonNumber constructJsonNumber(final Number number) {
        if (isInteger) {
            return createObjectBuilder().add(propertyName, (int) number).build().getJsonNumber(propertyName);
        }else{
            return createObjectBuilder().add(propertyName, (BigDecimal) number).build().getJsonNumber(propertyName);
        }
    }
}
