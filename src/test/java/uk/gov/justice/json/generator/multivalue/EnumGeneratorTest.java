package uk.gov.justice.json.generator.multivalue;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.json.generation.generators.values.EnumValueGenerator;
import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;

import java.util.List;

import org.junit.Test;

public class EnumGeneratorTest {

    private RandomListItemSelector randomListItemSelector = mock(RandomListItemSelector.class);

    @Test
    public void shouldAlwaysWrapStringsInQuotes() throws Exception {

        final String stringValue = "a value";
        final List<Object> enums = asList(stringValue, true, 23);

        final EnumGenerator enumGenerator = new EnumGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(stringValue);

        assertThat(enumGenerator.nextValue(), is(  stringValue ));
    }

    @Test
    public void shouldNeverWrapBooleansInQuotes() throws Exception {

        final Boolean booleanValue = true;
        final List<Object> enums = asList("a value", booleanValue, 23);

        final EnumGenerator enumValueGenerator = new EnumGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(booleanValue);

        assertThat(enumValueGenerator.nextValue(), is(booleanValue.toString()));
    }

    @Test
    public void shouldNeverWrapIntegersInQuotes() throws Exception {

        final Integer integerValue = 23;
        final List<Object> enums = asList("a value", true, integerValue);

        final EnumGenerator enumValueGenerator = new EnumGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(integerValue);

        assertThat(enumValueGenerator.nextValue(), is(integerValue.toString()));
    }

    @Test
    public void shouldNeverWrapDoublesInQuotes() throws Exception {

        final Double doubleValue = 23.23;
        final List<Object> enums = asList("a value", true, doubleValue);

        final EnumGenerator enumValueGenerator = new EnumGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(doubleValue);

        assertThat(enumValueGenerator.nextValue(), is(doubleValue.toString()));
    }
}
