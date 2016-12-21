package uk.gov.justice.json.generators.selectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import uk.gov.justice.json.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimePropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringPropertyGenerator;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;
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
        final Map<String, Object> propertyDefinitions = new HashMap<>();

        assumeThat(propertyDefinitions.isEmpty(), is(true));

        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions);

        assertThat(jsonPropertyGenerator, is(instanceOf(StringPropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldGetAnEmailPropertyGeneratorIfTheFormatIsEmail() throws Exception {

        final String propertyName = "emailProperty";

        final Map<String, Object> propertyDefinitions = new Builder<String, Object>()
                .put("format", "email")
                .build();

        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions);

        assertThat(jsonPropertyGenerator, is(instanceOf(EmailPropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldGetADateTimePropertyGeneratorIfTheFormatIsDateTime() throws Exception {

        final String propertyName = "dateTimeProperty";

        final Map<String, Object> propertyDefinitions = new Builder<String, Object>()
                .put("format", "date-time")
                .build();

        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions);

        assertThat(jsonPropertyGenerator, is(instanceOf(IsoDateTimePropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
    }

    @Test
    public void shouldGetARegexPropertyGeneratorIfPatternIsDefined() throws Exception {

        final String propertyName = "regexProperty";
        final String pattern = "$.my|regex[1].^";

        final Map<String, Object> propertyDefinitions = new Builder<String, Object>()
                .put("pattern", pattern)
                .build();

        final JsonPropertyGenerator jsonPropertyGenerator = stringPropertyGeneratorSelector.getStringPropertyGenerator(
                propertyName,
                propertyDefinitions);

        assertThat(jsonPropertyGenerator, is(instanceOf(RegexPropertyGenerator.class)));
        assertThat(jsonPropertyGenerator.getName(), is(propertyName));
        assertThat(((RegexPropertyGenerator) jsonPropertyGenerator).getPattern(), is(pattern));
    }
}
