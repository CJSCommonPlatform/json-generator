package uk.gov.justice.json.generation.schema;

import uk.gov.justice.json.generation.JsonGenerationException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Validator {

    private final String schemaDraft4 = loadSchemaDraft4("/json-schema-draft-04.json");

    private static String loadSchemaDraft4(final String name) {
        try {
            final File file = new File(Validator.class.getResource(name).toURI());

            return FileUtils.readFileToString(file);
        } catch (URISyntaxException | IOException e) {
            throw new JsonGenerationException("Failed to read schema draft 4 file " + name + " from classpath");
        }
    }

    public void validate(final String jsonSchema) {
        final JSONObject rawSchema = new JSONObject(new JSONTokener(schemaDraft4));
        final Schema schema = SchemaLoader.load(rawSchema);
        schema.validate(new JSONObject(jsonSchema));
    }
}
