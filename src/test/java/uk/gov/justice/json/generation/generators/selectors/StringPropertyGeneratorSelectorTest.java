package uk.gov.justice.json.generation.generators.selectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import uk.gov.justice.json.generation.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;

import org.everit.json.schema.StringSchema;
import org.everit.json.schema.internal.DateTimeFormatValidator;
import org.everit.json.schema.internal.EmailFormatValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class StringPropertyGeneratorSelectorTest {

    @InjectMocks
    private StringPropertyGeneratorSelector stringPropertyGeneratorSelector;

    @Test
    public void shouldGetAStringPropertyGeneratorIfFormatAndPatternAreUndefined() throws Exception {

        final String propertyName = "stringProperty";
        final StringSchema stringSchema = new StringSchema();

        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getPropertyGenerator(
                propertyName, stringSchema);

        assertThat(jsonPropertyGenerator, is(instanceOf(StringPropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldGetAnEmailPropertyGeneratorIfTheFormatIsEmail() throws Exception {

        final String propertyName = "emailProperty";
        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new EmailFormatValidator()).build();
        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getPropertyGenerator(
                propertyName, stringSchema);

        assertThat(jsonPropertyGenerator, is(instanceOf(EmailPropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldGetADateTimePropertyGeneratorIfTheFormatIsDateTime() throws Exception {

        final String propertyName = "dateTimeProperty";
        final StringSchema stringSchema = new StringSchema().builder().formatValidator(new DateTimeFormatValidator()).build();

        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getPropertyGenerator(
                propertyName, stringSchema);
        assertThat(jsonPropertyGenerator, is(instanceOf(IsoDateTimePropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldGetARegexPropertyGeneratorIfPatternIsDefined() throws Exception {

        final String propertyName = "regexProperty";
        final String pattern = "$.my|regex[1].^";
        final StringSchema stringSchema = new StringSchema().builder().pattern(pattern).build();
        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getPropertyGenerator(
                propertyName, stringSchema);

        assertThat(jsonPropertyGenerator, is(instanceOf(RegexPropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
        assertThat(((RegexPropertyGenerator) jsonPropertyGenerator).getPattern().pattern(), is(pattern));
    }
}
