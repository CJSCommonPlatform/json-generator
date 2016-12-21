package uk.gov.justice.json.schema;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.FileLoader;
import uk.gov.justice.json.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringPropertyGenerator;

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
        assertThat(jsonPropertyGenerators.get(0), is(instanceOf(StringPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(1).getName(), is("emailProperty"));
        assertThat(jsonPropertyGenerators.get(1), is(instanceOf(EmailPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(2).getName(), is("dateProperty"));
        assertThat(jsonPropertyGenerators.get(2), is(instanceOf(IsoDateTimePropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(3).getName(), is("integerProperty"));
        assertThat(jsonPropertyGenerators.get(3), is(instanceOf(IntegerPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(4).getName(), is("regExProperty"));
        assertThat(jsonPropertyGenerators.get(4), is(instanceOf(RegexPropertyGenerator.class)));
        assertThat(jsonPropertyGenerators.get(5).getName(), is("booleanProperty"));
        assertThat(jsonPropertyGenerators.get(5), is(instanceOf(BooleanPropertyGenerator.class)));
    }

    @Test
    public void shouldCreateGeneratorsForObjectProperties() throws Exception {

        final String jsonSchema = fileLoader.loadAsJsonSting("src/test/resources/object-property-schema.json");
        final JsonDocumentGenerator jsonDocumentGenerator = jsonSchemaParser.parse(jsonSchema);

        final List<JsonPropertyGenerator> jsonPropertyGenerators = jsonDocumentGenerator.getJsonPropertyGenerators();


        assertThat(jsonPropertyGenerators, hasSize(1));

        assertThat(jsonPropertyGenerators.get(0).getName(), is("objectProperty"));
        assertThat(jsonPropertyGenerators.get(0), is(instanceOf(ObjectPropertyGenerator.class)));
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
