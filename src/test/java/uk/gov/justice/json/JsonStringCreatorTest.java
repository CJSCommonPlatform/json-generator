package uk.gov.justice.json;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

public class JsonStringCreatorTest {

    private static final String DOUBLE_QUOTE = "\"";
    private final JsonStringCreator jsonStringCreator = new JsonStringCreator();
    private final SchemaLoader schemaLoader = new SchemaLoader();

    @Test
    public void shouldGenerateARandomAlphaNumericStringInQuotes() throws Exception {

        final int count = 1000;
        final Map<String, Object> jsonSchema = schemaLoader.loadSchema("src/test/resources/string-schema.json");
        final Map<String, Object> properties = (Map<String, Object>) jsonSchema.get("properties");

        for(int i = 0; i < count; i++) {
            final String randomString = jsonStringCreator.createRandomString(properties);

            assertThat(randomString, is(notNullValue()));
            assertThat(randomString.startsWith(DOUBLE_QUOTE), is(true));
            assertThat(randomString.endsWith(DOUBLE_QUOTE), is(true));
            assertThat(randomString.length(), is(12));
        }
    }

    @Test
    public void shouldGenerateARandomEmailInQuotesIfTheFormatIsEmail() throws Exception {

        final int count = 1000;
        final String emailRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        final Map<String, Object> jsonSchema = schemaLoader.loadSchema("src/test/resources/email-string-schema.json");
        final Map<String, Object> properties = (Map<String, Object>) jsonSchema.get("properties");

        for(int i = 0; i < count; i++) {
            final String randomEmail = jsonStringCreator.createRandomString(properties);

            assertThat(randomEmail, is(notNullValue()));
            assertThat(randomEmail.startsWith(DOUBLE_QUOTE), is(true));
            assertThat(randomEmail.endsWith(DOUBLE_QUOTE), is(true));
            assertThat(randomEmail.matches(emailRegEx), is(true));
        }
    }
}
