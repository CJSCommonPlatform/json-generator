package uk.gov.justice.json;

import static java.nio.charset.Charset.defaultCharset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

public class SchemaLoader {


    @SuppressWarnings("unchecked")
    public Map<String, Object> loadSchema(String filePath) throws IOException {

        final File file = new File(filePath);

        try(final FileInputStream inputStream = new FileInputStream(file)) {
            final String jsonSchema = IOUtils.toString(inputStream, defaultCharset());
            return new Gson().fromJson(jsonSchema, Map.class);
        }
    }
}
