package uk.gov.justice.json.generators.factories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FactoryProviderTest {


    private final FactoryProvider factoryProvider = new FactoryProvider();

    @Test
    public void shouldCreateAnObjectPropertyGeneratorFactory() throws Exception {

        final ObjectPropertyGeneratorFactory objectPropertyGeneratorFactory = factoryProvider.createNewObjectGeneratorFactory();

        assertThat(objectPropertyGeneratorFactory, is(notNullValue()));
        assertThat(factoryProvider.createNewObjectGeneratorFactory(), is(not(sameInstance(objectPropertyGeneratorFactory))));
    }

    @Test
    public void shouldCreateASimplePropertyGeneratorFactory() throws Exception {

        final BasicPropertyGeneratorFactory basicPropertyGeneratorFactory = factoryProvider.createNewPropertyGeneratorFactory();

        assertThat(basicPropertyGeneratorFactory, is(notNullValue()));
        assertThat(factoryProvider.createNewObjectGeneratorFactory(), is(not(sameInstance(basicPropertyGeneratorFactory))));
    }

    @Test
    public void shouldCreateAListArrayPropertyGeneratorFactory() throws Exception {

        final ListArrayPropertyGeneratorFactory listArrayPropertyGeneratorFactory = factoryProvider.createNewListArrayPropertyGeneratorFactory();

        assertThat(listArrayPropertyGeneratorFactory, is(notNullValue()));
        assertThat(factoryProvider.createNewListArrayPropertyGeneratorFactory(), is(not(sameInstance(listArrayPropertyGeneratorFactory))));
    }

    @Test
    public void shouldCreateATupleArrayPropertyGeneratorFactory() throws Exception {

        final TupleArrayPropertyGeneratorFactory tupleArrayPropertyGeneratorFactory = factoryProvider.createNewTupleArrayPropertyGeneratorFactory();

        assertThat(tupleArrayPropertyGeneratorFactory, is(notNullValue()));
        assertThat(factoryProvider.createNewTupleArrayPropertyGeneratorFactory(), is(not(sameInstance(tupleArrayPropertyGeneratorFactory))));
    }

    @Test
    public void shouldCreateAnUnspecifiedArrayPropertyGeneratorFactory() throws Exception {

        final UnspecifiedArrayPropertyGeneratorFactory unspecifiedArrayPropertyGeneratorFactory = factoryProvider.createNewUnspecifiedArrayPropertyGeneratorFactory();

        assertThat(unspecifiedArrayPropertyGeneratorFactory, is(notNullValue()));
        assertThat(factoryProvider.createNewUnspecifiedArrayPropertyGeneratorFactory(), is(not(sameInstance(unspecifiedArrayPropertyGeneratorFactory))));
    }
}
