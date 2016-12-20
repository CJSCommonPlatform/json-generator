package uk.gov.justice.json.schema;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.FileLoader;
import uk.gov.justice.json.generators.properties.BooleanJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimeJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringJsonPropertyGenerator;

import java.util.List;

import org.junit.Test;

public class JsonSchemaParserTest {

    private final JsonSchemaParser jsonSchemaParser = new JsonSchemaParser();
    private final FileLoader fileLoader = new FileLoader();


    @Test
    public void shouldCreateGeneratorsForSimpleProperties() throws Exception {

        final String jsonSchema = fileLoader.loadAsJsonSting("src/test/resources/simple-property-schema.json");
        final JsonDocumentGenerator jsonDocumentGenerator = jsonSchemaParser.parse(jsonSchema);
        final List<JsonPropertyGenerator> jsonPropertyGenerators = jsonDocumentGenerator.getJsonPropertyGenerators();

        assertThat(jsonPropertyGenerators, hasSize(6));

        assertThat(jsonPropertyGenerators.get(0).getName(), is("stringProperty"));
        assertThat(jsonPropertyGenerators.get(0), is(instanceOf(StringJsonPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(1).getName(), is("emailProperty"));
        assertThat(jsonPropertyGenerators.get(1), is(instanceOf(EmailJsonPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(2).getName(), is("dateProperty"));
        assertThat(jsonPropertyGenerators.get(2), is(instanceOf(IsoDateTimeJsonPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(3).getName(), is("integerProperty"));
        assertThat(jsonPropertyGenerators.get(3), is(instanceOf(IntegerJsonPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(4).getName(), is("regExProperty"));
        assertThat(jsonPropertyGenerators.get(4), is(instanceOf(RegexJsonPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(5).getName(), is("booleanProperty"));
        assertThat(jsonPropertyGenerators.get(5), is(instanceOf(BooleanJsonPropertyGenerator.class)));
    }

    @Test
    public void shouldCreateGeneratorsForObjectProperties() throws Exception {

        final String jsonSchema = fileLoader.loadAsJsonSting("src/test/resources/object-property-schema.json");
        final JsonDocumentGenerator jsonDocumentGenerator = jsonSchemaParser.parse(jsonSchema);

        final List<JsonPropertyGenerator> jsonPropertyGenerators = jsonDocumentGenerator.getJsonPropertyGenerators();


        assertThat(jsonPropertyGenerators, hasSize(1));

        assertThat(jsonPropertyGenerators.get(0).getName(), is("objectProperty"));
        assertThat(jsonPropertyGenerators.get(0), is(instanceOf(ObjectJsonPropertyGenerator.class)));
    }

    @Test
    public void shouldCreateGeneratorsForEnumProperties() throws Exception {

        final String jsonSchema = fileLoader.loadAsJsonSting("src/test/resources/enum-property-schema.json");

        final JsonDocumentGenerator jsonDocumentGenerator = jsonSchemaParser.parse(jsonSchema);

        final String json = jsonDocumentGenerator.generate();
        System.out.println(json);
    }

    @Test
    public void shouldCreateGeneratorsForArrayProperties() throws Exception {

        final String jsonSchema = fileLoader.loadAsJsonSting("src/test/resources/array-property-schema.json");

        final JsonDocumentGenerator jsonDocumentGenerator = jsonSchemaParser.parse(jsonSchema);

        final String json = jsonDocumentGenerator.generate();
        System.out.println(json);
    }
}
