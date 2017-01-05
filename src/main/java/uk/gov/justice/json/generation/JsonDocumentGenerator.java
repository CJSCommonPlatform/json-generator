package uk.gov.justice.json.generation;

import static org.apache.commons.io.FileUtils.readFileToString;

import uk.gov.justice.json.generation.schema.JsonGenerator;
import uk.gov.justice.json.generation.schema.JsonSchemaParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.json.JsonObject;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.io.IOUtils;

public class JsonDocumentGenerator {

    private final JsonSchemaParser jsonSchemaParser;

    public JsonDocumentGenerator() {
        this(new JsonSchemaParser());
    }

    @VisibleForTesting
    JsonDocumentGenerator(final JsonSchemaParser jsonSchemaParser) {
        this.jsonSchemaParser = jsonSchemaParser;
    }

    public JsonGenerator generateJsonFrom(final String schema) {
        return jsonSchemaParser.parse(schema);
    }

    public JsonGenerator generateJsonFrom(final InputStream schemaStream) {

        try {
            final String schema = IOUtils.toString(schemaStream);
            return generateJsonFrom(schema);
        } catch (IOException e) {
            throw new JsonGenerationException("Failed to read json schema from InputStream", e);
        }
    }

    public JsonGenerator generateJsonFrom(final File schemaFile) {

        try {
            final String schema = readFileToString(schemaFile);
            return generateJsonFrom(schema);
        } catch (IOException e) {
            throw new JsonGenerationException("Failed to read json schema from File: " + schemaFile.getAbsolutePath(), e);
        }
    }

    public JsonGenerator generateJsonFrom(final Reader schemaReader) {
        try {
            final String schema = IOUtils.toString(schemaReader);
            return generateJsonFrom(schema);
        } catch (IOException e) {
            throw new JsonGenerationException("Failed to read json schema from Reader", e);
        }
    }

    public JsonGenerator generateJsonFrom(final JsonObject schemaJsonObject) {
        final String schema = schemaJsonObject.toString();
        return generateJsonFrom(schema);
    }
}
