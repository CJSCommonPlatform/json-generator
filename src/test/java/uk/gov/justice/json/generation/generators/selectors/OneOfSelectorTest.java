package uk.gov.justice.json.generation.generators.selectors;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;

import org.everit.json.schema.Schema;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.StringPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


//@RunWith(MockitoJUnitRunner.class)
public class OneOfSelectorTest {

    @Mock
    private RandomListItemSelector randomListItemSelector;

    @InjectMocks
    private OneOfSelector oneOfSelector;

    //@Test
    public void shouldRandomlySelectAnObjectAndReturnItsGenerator() throws Exception {

        final String propertyName = "oneOfProperty";
        final List<Schema> objects = new ArrayList<>();

        final Map<String, Object> objectDefinition = new Builder<String, Object>()
                .put("properties", new Builder<String, Object>()
                        .put("stringProperty", new Builder<String, Object>()
                                .put("type", "string")
                                .build())
                        .build())
                .build();

        when(randomListItemSelector.selectRandomlyFrom1(objects)).thenReturn(objectDefinition);

//        final ObjectPropertyGenerator objectPropertyGenerator = oneOfSelector.getOneOf(propertyName, objects);
//
//        assertThat(objectPropertyGenerator.getName(), is(propertyName));
//        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().size(), is(1));
//        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().get(0), is(instanceOf(StringPropertyGenerator.class)));
//        assertThat(objectPropertyGenerator.getJsonPropertyGenerators().get(0).getName(), is("stringProperty"));
    }
}
