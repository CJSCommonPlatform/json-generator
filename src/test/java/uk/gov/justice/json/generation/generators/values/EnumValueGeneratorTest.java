package uk.gov.justice.json.generation.generators.values;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class EnumValueGeneratorTest {

    private RandomListItemSelector randomListItemSelector = mock(RandomListItemSelector.class);

    @Test
    public void shouldAlwaysWrapStringsInQuotes() throws Exception {

        final String stringValue = "a value";
        final List<Object> enums = asList(stringValue, true, 23);

        final EnumValueGenerator enumValueGenerator = new EnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(stringValue);

        assertThat(enumValueGenerator.nextValue(), is(DOUBLE_QUOTE + stringValue + DOUBLE_QUOTE));
    }

    @Test
    public void shouldNeverWrapBooleansInQuotes() throws Exception {

        final Boolean booleanValue = true;
        final List<Object> enums = asList("a value", booleanValue, 23);

        final EnumValueGenerator enumValueGenerator = new EnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(booleanValue);

        assertThat(enumValueGenerator.nextValue(), is(booleanValue.toString()));
    }

    @Test
    public void shouldNeverWrapIntegersInQuotes() throws Exception {

        final Integer integerValue = 23;
        final List<Object> enums = asList("a value", true, integerValue);

        final EnumValueGenerator enumValueGenerator = new EnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(integerValue);

        assertThat(enumValueGenerator.nextValue(), is(integerValue.toString()));
    }

    @Test
    public void shouldNeverWrapDoublesInQuotes() throws Exception {

        final Double doubleValue = 23.23;
        final List<Object> enums = asList("a value", true, doubleValue);

        final EnumValueGenerator enumValueGenerator = new EnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(doubleValue);

        assertThat(enumValueGenerator.nextValue(), is(doubleValue.toString()));
    }
}
