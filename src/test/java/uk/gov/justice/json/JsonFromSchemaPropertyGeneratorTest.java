package uk.gov.justice.json;

import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.google.common.base.Charsets.UTF_8;
import static java.nio.charset.Charset.defaultCharset;
import static org.junit.Assert.fail;

public class JsonFromSchemaPropertyGeneratorTest {

    private static Schema getJsonSchemaFor(final String pathToJsonSchema) throws IOException {
        final String jsonSchema = getJsonContentFrom(pathToJsonSchema);
        final JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchema));
        return SchemaLoader.load(rawSchema);
    }

    private static String getJsonContentFrom(final String pathToJsonSchema) throws IOException {
        final String jsonContent;
        if (Paths.get(pathToJsonSchema).isAbsolute()) {
            jsonContent = com.google.common.io.Files.toString(new File(pathToJsonSchema), UTF_8);
        } else {
            jsonContent = Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
        }
        return jsonContent;
    }

    @Test
    public void shouldName() throws Exception {

        final File file = new File("src/test/resources/support.support-request.json");

        final String jsonSchema = IOUtils.toString(new FileInputStream(file), defaultCharset());
        final JsonFromSchemaGenerator schemaGenerator = new JsonFromSchemaGenerator(jsonSchema);

        final String json = schemaGenerator.next();

        System.out.println(json);
    }

    @Ignore
    @Test
    public void shouldGenerateRandomJsonFromSchema() throws IOException {
        File resourcesDirectory = new File("src/test/resources");
        System.out.println(resourcesDirectory.getAbsolutePath());
        try (Stream<Path> paths = Files.walk(resourcesDirectory.toPath())) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String fileName = filePath.getFileName().toString();
                    final boolean isValid = validateRandomJsonGenerator(fileName);

                    System.out.println(fileName + " : " + isValid);
                }
            });
        }
    }

    @Ignore
    @Test
    public void verifyFile() {
        // given
//        String pathToJsonSchema = "example.add-recipe.json";
//        String pathToJsonSchema = "example.add-recipe-file.json";
//        String pathToJsonSchema = "example.make-cake.json";
//        String pathToJsonSchema = "example.order-cake.json";
        String pathToJsonSchema = "charging.command.update-review.json";
        JsonFromSchemaGenerator jsonFromSchemaGenerator = new JsonFromSchemaGenerator(pathToJsonSchema);

        // when
        String json = jsonFromSchemaGenerator.next();

        try {
            System.out.println(json);
            getJsonSchemaFor(pathToJsonSchema).validate(new JSONObject(json));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    private boolean validateRandomJsonGenerator(String pathToJsonSchema) {
        try {
            JsonFromSchemaGenerator jsonFromSchemaGenerator = new JsonFromSchemaGenerator(pathToJsonSchema);
            // when
            String json = jsonFromSchemaGenerator.next();

            final Schema jsonSchema = getJsonSchemaFor(pathToJsonSchema);
            jsonSchema.validate(new JSONObject(json));
        } catch (Throwable e) {
            return false;
        }
        return true;
    }
}
