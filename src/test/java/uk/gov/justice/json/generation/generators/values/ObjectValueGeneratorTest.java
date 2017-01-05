package uk.gov.justice.json.generation.generators.values;

import static com.jayway.jsonassert.JsonAssert.with;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

import java.util.List;

import org.junit.Test;

public class ObjectValueGeneratorTest {

    @Test
    public void shouldCreateObjectJsonUsingThePropertyGenerators() throws Exception {

        final String aStringJsonProperty = "\"name\": \"Fred Bloggs\"";
        final String anIntegerJsonProperty = "\"age\": 12";
        final String anEmailJsonProperty = "\"email\": \"fred.bloggs@gerrit.com\"";

        final EmailPropertyGenerator emailPropertyGenerator = mock(EmailPropertyGenerator.class);
        final IntegerPropertyGenerator integerPropertyGenerator = mock(IntegerPropertyGenerator.class);
        final StringPropertyGenerator stringPropertyGenerator = mock(StringPropertyGenerator.class);

        when(emailPropertyGenerator.nextJson()).thenReturn(anEmailJsonProperty);
        when(integerPropertyGenerator.nextJson()).thenReturn(anIntegerJsonProperty);
        when(stringPropertyGenerator.nextJson()).thenReturn(aStringJsonProperty);

        final List<JsonPropertyGenerator> jsonPropertyGenerators = asList(
                stringPropertyGenerator,
                integerPropertyGenerator,
                emailPropertyGenerator);

        final ObjectValueGenerator objectValueGenerator = new ObjectValueGenerator(jsonPropertyGenerators);

        final String json = objectValueGenerator.nextValue();

        with(json)
                .assertThat("$.name", is("Fred Bloggs"))
                .assertThat("$.age", is(12))
                .assertThat("$.email", is("fred.bloggs@gerrit.com"))
        ;
    }

    @Test
    public void shouldHandleJustOnePropertyGenerator() throws Exception {

        final String aStringJsonProperty = "\"name\": \"Fred Bloggs\"";
        final StringPropertyGenerator stringPropertyGenerator = mock(StringPropertyGenerator.class);

        when(stringPropertyGenerator.nextJson()).thenReturn(aStringJsonProperty);

        final ObjectValueGenerator objectValueGenerator = new ObjectValueGenerator(singletonList(stringPropertyGenerator));
        final String json = objectValueGenerator.nextValue();

        with(json)
                .assertThat("$.name", is("Fred Bloggs"))
        ;
    }
}
