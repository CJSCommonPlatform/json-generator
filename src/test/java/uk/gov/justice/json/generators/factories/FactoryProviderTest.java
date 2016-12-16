package uk.gov.justice.json.generators.factories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

        final SimplePropertyGeneratorFactory simplePropertyGeneratorFactory = factoryProvider.createNewPropertyGeneratorFactory();

        assertThat(simplePropertyGeneratorFactory, is(notNullValue()));
        assertThat(factoryProvider.createNewObjectGeneratorFactory(), is(not(sameInstance(simplePropertyGeneratorFactory))));
    }
}
