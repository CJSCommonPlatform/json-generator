package uk.gov.justice.json.generation.generators.selectors.arrays;

import static java.lang.String.format;

import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.selectors.SelectorFactory;

import java.util.List;
import java.util.Map;

public class ArrayGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator getArrayGenerator(final String propertyName, final Map<String, Object> propertyDefinitions) {

        final Object items = propertyDefinitions.get("items");

        // unspecifiedArrayProperty
        if (items == null) {
            return selectorFactory
                    .createNewUnspecifiedArrayGeneratorSelector()
                    .createGenerator(propertyName);
        }

        // tupleArrayProperty
        if (items instanceof List) {
            final List<Map<String, Object>> itemsList = (List<Map<String, Object>>) items;
            return selectorFactory
                    .createNewTupleArrayGeneratorSelector()
                    .createGenerator(propertyName, itemsList);
        }

        // listArrayProperty
        if (items instanceof Map) {
            final Map<String, Object> itemsMap = (Map<String, Object>) items;
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
