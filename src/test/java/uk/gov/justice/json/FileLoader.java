package uk.gov.justice.json;

import static java.nio.charset.Charset.defaultCharset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileLoader {

    public String loadAsJsonSting(final String pathname) throws IOException {
        final File file = new File(pathname);
        return IOUtils.toString(new FileInputStream(file), defaultCharset());
    }
}
