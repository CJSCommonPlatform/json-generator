package uk.gov.justice.json.generation.schema;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.everit.json.schema.ValidationException;
import org.junit.Test;

public class ValidatorTest {

    private Validator validator = new Validator();

    @Test
    public void shouldValidateAValidSchema() throws Exception {
        validator.validate(loadAsString("/simple-property-schema.json"));
    }

    @Test
    public void shouldFailValidationAgainstAnInvalidSchema() throws Exception {

        final String schema = loadAsString("/invalid-schema.json");

        try {
            validator.validate(schema);
            fail();
        } catch (ValidationException expected) {
            final List<ValidationException> causingExceptions = expected.getCausingExceptions();
            assertThat(causingExceptions, hasSize(2));
        }
    }

    private String loadAsString(final String schemaPath) throws URISyntaxException, IOException {
        final File file = new File(Validator.class.getResource(schemaPath).toURI());
        return FileUtils.readFileToString(file);
    }
}
