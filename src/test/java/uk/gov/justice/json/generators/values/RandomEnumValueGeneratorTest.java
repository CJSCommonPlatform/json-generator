package uk.gov.justice.json.generators.values;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import uk.gov.justice.json.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class RandomEnumValueGeneratorTest {

    private RandomListItemSelector randomListItemSelector = mock(RandomListItemSelector.class);

    @Test
    public void shouldAlwaysWrapStringsInQuotes() throws Exception {

        final String stringValue = "a value";
        final List<Object> enums = asList(stringValue, true, 23);

        final RandomEnumValueGenerator randomEnumValueGenerator = new RandomEnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(stringValue);

        assertThat(randomEnumValueGenerator.nextValue(), is(DOUBLE_QUOTE + stringValue + DOUBLE_QUOTE));
    }

    @Test
    public void shouldNeverWrapBooleansInQuotes() throws Exception {

        final Boolean booleanValue = true;
        final List<Object> enums = asList("a value", booleanValue, 23);

        final RandomEnumValueGenerator randomEnumValueGenerator = new RandomEnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(booleanValue);

        assertThat(randomEnumValueGenerator.nextValue(), is(booleanValue.toString()));
    }

    @Test
    public void shouldNeverWrapIntegersInQuotes() throws Exception {

        final Integer integerValue = 23;
        final List<Object> enums = asList("a value", true, integerValue);

        final RandomEnumValueGenerator randomEnumValueGenerator = new RandomEnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(integerValue);

        assertThat(randomEnumValueGenerator.nextValue(), is(integerValue.toString()));
    }

    @Test
    public void shouldNeverWrapDoublesInQuotes() throws Exception {

        final Double doubleValue = 23.23;
        final List<Object> enums = asList("a value", true, doubleValue);

        final RandomEnumValueGenerator randomEnumValueGenerator = new RandomEnumValueGenerator(
                enums,
                randomListItemSelector);

        when(randomListItemSelector.selectRandomlyFrom(enums)).thenReturn(doubleValue);

        assertThat(randomEnumValueGenerator.nextValue(), is(doubleValue.toString()));
    }
}
