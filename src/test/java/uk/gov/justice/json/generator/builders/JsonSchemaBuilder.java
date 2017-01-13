package uk.gov.justice.json.generator.builders;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JsonSchemaBuilder {

        JsonObjectBuilder builder = Json.createObjectBuilder();

        public JsonObject build() {
            return withProperties()
                    .add("required", createArrayBuilder()
                            .add("exampleNumber"))
                    .build();
        }

        private JsonObjectBuilder withProperties() {
            return builder
                    .add("properties", createObjectBuilder()
                            .add("exampleNumber", createObjectBuilder()
                                    .add("enum", createArrayBuilder()
                                            .add("amber")
                                            .add("green"))));
        }

        private JsonObjectBuilder createSchema() {
            return builder
                    .add("$schema", "http://json-schema.org/draft-04/schema#")
                    .add("type", "object");
        }
}
