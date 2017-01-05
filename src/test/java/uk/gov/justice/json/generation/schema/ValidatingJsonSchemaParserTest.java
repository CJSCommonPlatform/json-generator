package uk.gov.justice.json.generation.schema;

import org.apache.commons.io.FileUtils;
import org.everit.json.schema.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidatingJsonSchemaParserTest {

    @Mock
    JsonSchemaParser jsonSchemaParser;

    @Mock
    Validator validator;

    @InjectMocks
    ValidatingJsonSchemaParser validatingJsonSchemaParser;

    @Test
    public void shouldValidateAndParseAValidSchema() throws Exception {
        final String schema = loadAsString("/simple-property-schema.json");

        validatingJsonSchemaParser.validateAndParse(schema);

        verify(validator).validate(schema);
        verify(jsonSchemaParser).parse(schema);
    }


    @Test
    public void shouldFailOnValidatingOnIncorrectSchema() throws Exception{
        final String schema = loadAsString("/invalid-schema.json");
        doThrow(ValidationException.class).when(validator).validate(schema);
        try {
            validatingJsonSchemaParser.validateAndParse(schema);
            fail();
        } catch (ValidationException expected) {
            verifyNoMoreInteractions(jsonSchemaParser);
        }
    }

    private String loadAsString(final String schemaPath) throws URISyntaxException, IOException {
        final File file = new File(Validator.class.getResource(schemaPath).toURI());
        return FileUtils.readFileToString(file);
    }
}