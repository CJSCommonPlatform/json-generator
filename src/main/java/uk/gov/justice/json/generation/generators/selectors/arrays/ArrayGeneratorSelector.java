package uk.gov.justice.json.generation.generators.selectors.arrays;

import static java.lang.String.format;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.selectors.SelectorFactory;

import java.util.List;
import java.util.Map;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.Schema;

public class ArrayGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator getArrayGenerator(final String propertyName, final ArraySchema propertyDefinitions) {

        final Object items = propertyDefinitions.getItemSchemas();

        // unspecifiedArrayProperty
        if (items == null) {
            // return selectorFactory.createNewBoundedArrayGeneratorSelector().createGenerator(propertyName, propertyDefinitions.getMinItems(),propertyDefinitions.getMaxItems());

            return selectorFactory
                    .createNewUnspecifiedArrayGeneratorSelector()
                    .createGenerator(propertyName);
        }

        // tupleArrayProperty
        if (items instanceof List) {
            final List<Schema> itemsList = (List<Schema>) items;
            return selectorFactory
                    .createNewTupleArrayGeneratorSelector()
                    .createGenerator(propertyName, itemsList);
        }

        // listArrayProperty
        if (items instanceof Map) {
            final Map<String, EnumSchema> itemsMap = (Map<String, EnumSchema>) items;
            return selectorFactory
                    .createNewListArrayGeneratorSelector()
                    .createGenerator(propertyName, itemsMap);
        }

        throw new RuntimeException(format(
                "Error creating array property '%s'. Unknown array type: %s",
                propertyName,
                items.getClass().getSimpleName()));
    }
}
