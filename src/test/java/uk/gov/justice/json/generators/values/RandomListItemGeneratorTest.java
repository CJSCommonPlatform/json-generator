package uk.gov.justice.json.generators.values;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

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

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class RandomListItemGeneratorTest {

    @Mock
    private Random random;

    @InjectMocks
    private RandomListItemGenerator randomListItemGenerator;

    @Test
    public void shouldRandomlySelectAnItemFromTheList() throws Exception {

        final List<Object> values = asList("fred", 23, true, 3.14);

        when(random.nextInt(values.size())).thenReturn(0);
        assertThat(randomListItemGenerator.selectRandomlyFrom(values), is("fred"));

        when(random.nextInt(values.size())).thenReturn(1);
        assertThat(randomListItemGenerator.selectRandomlyFrom(values), is(23));

        when(random.nextInt(values.size())).thenReturn(2);
        assertThat(randomListItemGenerator.selectRandomlyFrom(values), is(true));

        when(random.nextInt(values.size())).thenReturn(3);
        assertThat(randomListItemGenerator.selectRandomlyFrom(values), is(3.14));
    }
}
