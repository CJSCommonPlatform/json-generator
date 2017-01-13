package uk.gov.justice.json.generator.value;


import static javax.json.Json.createObjectBuilder;

import uk.gov.justice.json.generator.JsonObjectGenerator;
import uk.gov.justice.json.generator.JsonStringGenerator;
import uk.gov.justice.json.generator.JsonValueGenerator;

import java.io.StringReader;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;

public class EnumGenerator extends JsonValueGenerator {


    private EnumSchema schema;

    public EnumGenerator(Schema schema) {

        this.schema = (EnumSchema) schema;
    }


    @Override
    public JsonValue next() {

        final Object value = randomlyGet(schema.getPossibleValues());

        if (value instanceof String) {
            return new JsonStringGenerator((StringSchema) value).next();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final String valueAsString = objectMapper.writeValueAsString(value);
            final JsonParser parser = Json.createParser(new StringReader(valueAsString));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new JsonObjectGenerator((ObjectSchema) value).next();
    }

    private Object randomlyGet(Set<Object> possibleValues) {
        return possibleValues.iterator().next();
    }

    private JsonString constructJsonString(final String string) {
        return createObjectBuilder().add("tmp", string).build().getJsonString("tmp");
    }


}
