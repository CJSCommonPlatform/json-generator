package uk.gov.justice.json.schema;

import static com.jayway.jsonassert.JsonAssert.with;
import static java.nio.charset.Charset.defaultCharset;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import uk.gov.justice.json.PropertyGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class JsonSchemaParserTest {

    private JsonSchemaParser jsonSchemaParser = new JsonSchemaParser();

    @Test
    public void shouldCreateGeneratorsForSimpleProperties() throws Exception {

        final File file = new File("src/test/resources/simple-property-schema.json");

        final String jsonSchema = IOUtils.toString(new FileInputStream(file), defaultCharset());

        final JsonDocumentGenerator jsonDocumentGenerator = jsonSchemaParser.parse(jsonSchema);

        final List<PropertyGenerator> propertyGenerators = jsonDocumentGenerator.getPropertyGenerators();

        assertThat(propertyGenerators, hasSize(6));

        assertThat(propertyGenerators.get(0).getName(), is("stringProperty"));
        assertThat(propertyGenerators.get(0), is(instanceOf(StringPropertyGenerator.class)));
        assertThat(propertyGenerators.get(1).getName(), is("emailProperty"));
        assertThat(propertyGenerators.get(1), is(instanceOf(EmailPropertyGenerator.class)));
        assertThat(propertyGenerators.get(2).getName(), is("dateProperty"));
        assertThat(propertyGenerators.get(2), is(instanceOf(IsoDateTimePropertyGenerator.class)));
        assertThat(propertyGenerators.get(3).getName(), is("integerProperty"));
        assertThat(propertyGenerators.get(3), is(instanceOf(IntegerPropertyGenerator.class)));
        assertThat(propertyGenerators.get(4).getName(), is("regExProperty"));
        assertThat(propertyGenerators.get(4), is(instanceOf(RegexPropertyGenerator.class)));
        assertThat(propertyGenerators.get(5).getName(), is("booleanProperty"));
        assertThat(propertyGenerators.get(5), is(instanceOf(BooleanPropertyGenerator.class)));
    }
}
