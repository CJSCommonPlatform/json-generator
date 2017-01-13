package uk.gov.justice.json.generator;

import static org.apache.commons.io.FileUtils.readFileToString;

import uk.gov.justice.json.generation.schema.JsonParser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

public class JsonFileHelper {

    public static final JsonFileHelper INSTANCE = new JsonFileHelper();

    private JsonFileHelper() {
    }

    public static JsonFileHelper getInstance() {
        return INSTANCE;
    }

    public Schema getFileAsSchema(final String filePath) throws URISyntaxException, IOException {

        return getSchemaFromString(readFileToString(new File(this.getClass().getResource(filePath).toURI())));

    }
    
    public Schema getSchemaFromString(String json) {
        final JsonParser jsonParser = new JsonParser();
        final SchemaLoader schemaLoader = jsonParser.toSchemaLoader(json);
        return schemaLoader.load().build();
    }
}
