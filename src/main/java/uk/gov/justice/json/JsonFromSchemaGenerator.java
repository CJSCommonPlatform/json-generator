package uk.gov.justice.json;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.mifmif.common.regex.Generex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.google.common.base.Charsets.UTF_8;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * Generates random json from a given JSON schema location
 */
public class JsonFromSchemaGenerator extends Generator<String> {

    private final Map rootJsonObj;
    private Random random = new Random();

    public JsonFromSchemaGenerator(String jsonSchema) {
        rootJsonObj = new Gson().fromJson(jsonSchema, Map.class);
    }

    public String next() {
        return new JsonFromSchemaMapCreator().processSchemaJsonObjectForType(rootJsonObj);
    }


    private String getSchemaContent(String pathToJsonSchema) {
        String schemaContent;
        try {
            if (Paths.get(pathToJsonSchema).isAbsolute()) {
                schemaContent = Files.toString(new File(pathToJsonSchema), UTF_8);
            } else {
                schemaContent = Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return schemaContent;
    }
}
