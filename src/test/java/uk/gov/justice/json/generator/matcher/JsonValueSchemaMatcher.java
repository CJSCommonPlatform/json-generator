package uk.gov.justice.json.generator.matcher;

import javax.json.JsonValue;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonValueSchemaMatcher {

    public static Matcher<JsonValue> isValidForSchema(final Schema schema) {

        return new TypeSafeMatcher<JsonValue>() {
            private String pathToJsonFile;
            private Exception exception = null;

            @Override
            protected boolean matchesSafely(final JsonValue jsonValue) {
                return validateJsonObject(schema, jsonValue);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("json file ").appendValue(pathToJsonFile)
                        .appendText(" to validate against schema ");
            }

            @Override
            protected void describeMismatchSafely(final JsonValue jsonValue, final Description mismatchDescription) {
                mismatchDescription.appendText("validation failed with message ").appendValue(exception.getMessage());
            }
        };
    }

    private static Boolean validateJsonObject(final Schema schema, final JsonValue jsonValue) {

        try {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                schema.validate(new JSONObject(jsonValue.toString()));
            }
            if (jsonValue.getValueType() == JsonValue.ValueType.STRING) {
                schema.validate(jsonValue.toString());
            }
            if (jsonValue.getValueType() == JsonValue.ValueType.ARRAY) {
                schema.validate(new JSONArray(jsonValue.toString()));
            }
            if (jsonValue.getValueType() == JsonValue.ValueType.NUMBER) {
                schema.validate(jsonValue.toString());
            }
        } catch (ValidationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
