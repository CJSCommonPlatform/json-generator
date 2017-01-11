package uk.gov.justice.json.generator;

import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.value.IntegerGenerator;
import uk.gov.justice.json.generator.value.NumberGenerator;

import java.math.BigInteger;

import javax.json.JsonNumber;
import javax.json.JsonValue;

import org.everit.json.schema.NumberSchema;

public class JsonNumberGenerator implements JsonValueGenerator<JsonNumber>{

    private final String propertyName;
    private NumberGenerator numberGenerator;

    public JsonNumberGenerator(final String propertyName,final NumberSchema numberSchema) {
        this.propertyName =propertyName;
        numberGenerator = new IntegerGenerator();
    }

    @Override
    public JsonNumber nextValue() {
        return constructJsonNumber(numberGenerator.nextValue());
    }

    private JsonNumber constructJsonNumber(final Number number) {
        return createObjectBuilder().add(propertyName,(int)number).build().getJsonNumber(propertyName);
    }
}
