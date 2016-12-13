package uk.gov.justice.json.generation;

import static javax.json.Json.createReader;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.schema.JsonGenerator;
import uk.gov.justice.json.generation.schema.JsonSchemaParser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;

import javax.json.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class JsonDocumentGeneratorTest {

    @Mock
    private JsonSchemaParser jsonSchemaParser;

    @InjectMocks
    private JsonDocumentGenerator jsonDocumentGenerator;

    @Test
    public void shouldGenerateJsonFromAStringSchemaInput() throws Exception {

        final String schema = "this is json schema";
        final JsonGenerator jsonGenerator = mock(JsonGenerator.class);

        when(jsonSchemaParser.parse(schema)).thenReturn(jsonGenerator);

        assertThat(this.jsonDocumentGenerator.generateJsonFrom(schema), is(jsonGenerator));
    }

    @Test
    public void shouldGenerateJsonFromAnInputStreamSchemaInput() throws Exception {

        final String schema = "this is json schema";
        final InputStream schemaStream = new ByteArrayInputStream(schema.getBytes());
        final JsonGenerator jsonGenerator = mock(JsonGenerator.class);

        when(jsonSchemaParser.parse(schema)).thenReturn(jsonGenerator);

        assertThat(jsonDocumentGenerator.generateJsonFrom(schemaStream), is(jsonGenerator));
    }

    @Test
    public void shouldGenerateJsonFromAFileSchemaInput() throws Exception {

        final File schemaFile = getFile("/simple-property-schema.json");
        final String schema = readFileToString(schemaFile);

        final JsonGenerator jsonGenerator = mock(JsonGenerator.class);

        when(jsonSchemaParser.parse(schema)).thenReturn(jsonGenerator);

        assertThat(this.jsonDocumentGenerator.generateJsonFrom(schemaFile), is(jsonGenerator));
    }

    @Test
    public void shouldGenerateJsonFromAReaderSchemaInput() throws Exception {

        final String schema = "this is json schema";
        final Reader schemaReader = new StringReader(schema);
        final JsonGenerator jsonGenerator = mock(JsonGenerator.class);

        when(jsonSchemaParser.parse(schema)).thenReturn(jsonGenerator);

        assertThat(this.jsonDocumentGenerator.generateJsonFrom(schemaReader), is(jsonGenerator));
    }

    @Test
    public void shouldGenerateJsonFromAJsonObjectSchemaInput() throws Exception {

        final File schemaFile = getFile("/simple-property-schema.json");
        final String schema = readFileToString(schemaFile);
        final JsonObject schemaJsonObject = toJsonObject(schema);

        final JsonGenerator jsonGenerator = mock(JsonGenerator.class);

        when(jsonSchemaParser.parse(schemaJsonObject.toString())).thenReturn(jsonGenerator);

        assertThat(this.jsonDocumentGenerator.generateJsonFrom(schemaJsonObject), is(jsonGenerator));
    }

    private JsonObject toJsonObject(final String json) {
        return createReader(new StringReader(json)).readObject();
    }

    private File getFile(final String filePath) throws URISyntaxException {
        return new File(this.getClass().getResource(filePath).toURI());
    }
}
