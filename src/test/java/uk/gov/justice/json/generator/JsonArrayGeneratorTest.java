package uk.gov.justice.json.generator;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MAX_ITEMS;
import static uk.gov.justice.json.generator.JsonArrayGenerator.DEFAULT_MIN_ITEMS;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.Times.times;
import static uk.gov.justice.services.test.utils.core.helper.TypeCheck.typeCheck;

import uk.gov.justice.json.JsonSchemaLoader;

import javax.json.JsonArray;

import org.everit.json.schema.ArraySchema;
import org.junit.Test;

/**
 * Unit tests for the {@link JsonArrayGenerator} class.
 */
public class JsonArrayGeneratorTest {

    private static final int NUMBER_OF_TIMES = 1000;

    @Test
    public void shouldGenerateUnboundedArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/strings-schema.json");
        final JsonArrayGenerator generator = new JsonArrayGenerator(schema);

        typeCheck(generator, array -> array.size() >= DEFAULT_MIN_ITEMS & array.size() <= DEFAULT_MAX_ITEMS)
                .verify(times(NUMBER_OF_TIMES));
    }

    @Test
    public void shouldGenerateBoundedArrays() {
        final ArraySchema schema = JsonSchemaLoader.loadArraySchema("src/test/resources/schemas/array/bounded-strings-schema.json");
        final JsonArrayGenerator generator = new JsonArrayGenerator(schema);

        typeCheck(generator, array -> array.size() >= 3 & array.size() <= 37)
                .verify(times(NUMBER_OF_TIMES));
    }
}
