package uk.gov.justice.json.generators.selectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SelectorFactoryTest {


    private final SelectorFactory selectorFactory = new SelectorFactory();

    @Test
    public void shouldCreateAnObjectPropertyGeneratorFactory() throws Exception {

        final ObjectGeneratorSelector objectGeneratorSelector = selectorFactory.createNewObjectGeneratorSelector();

        assertThat(objectGeneratorSelector, is(notNullValue()));
        assertThat(selectorFactory.createNewObjectGeneratorSelector(), is(not(sameInstance(objectGeneratorSelector))));
    }

    @Test
    public void shouldCreateASimplePropertyGeneratorFactory() throws Exception {

        final PropertyGeneratorSelector propertyGeneratorSelector = selectorFactory.createNewPropertyGeneratorSelector();

        assertThat(propertyGeneratorSelector, is(notNullValue()));
        assertThat(selectorFactory.createNewObjectGeneratorSelector(), is(not(sameInstance(propertyGeneratorSelector))));
    }

    @Test
    public void shouldCreateAListArrayPropertyGeneratorFactory() throws Exception {

        final ListArrayGeneratorSelector listArrayGeneratorSelector = selectorFactory.createNewListArrayGeneratorSelector();

        assertThat(listArrayGeneratorSelector, is(notNullValue()));
        assertThat(selectorFactory.createNewListArrayGeneratorSelector(), is(not(sameInstance(listArrayGeneratorSelector))));
    }

    @Test
    public void shouldCreateATupleArrayPropertyGeneratorFactory() throws Exception {

        final TupleArrayGeneratorSelector tupleArrayGeneratorSelector = selectorFactory.createNewTupleArrayGeneratorSelector();

        assertThat(tupleArrayGeneratorSelector, is(notNullValue()));
        assertThat(selectorFactory.createNewTupleArrayGeneratorSelector(), is(not(sameInstance(tupleArrayGeneratorSelector))));
    }

    @Test
    public void shouldCreateAnUnspecifiedArrayPropertyGeneratorFactory() throws Exception {

        final UnspecifiedArrayGeneratorSelector unspecifiedArrayGeneratorSelector = selectorFactory.createNewUnspecifiedArrayGeneratorSelector();

        assertThat(unspecifiedArrayGeneratorSelector, is(notNullValue()));
        assertThat(selectorFactory.createNewUnspecifiedArrayGeneratorSelector(), is(not(sameInstance(unspecifiedArrayGeneratorSelector))));
    }
}
