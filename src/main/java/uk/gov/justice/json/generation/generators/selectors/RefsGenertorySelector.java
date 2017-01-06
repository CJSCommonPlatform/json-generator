package uk.gov.justice.json.generation.generators.selectors;

import uk.gov.justice.json.generation.generators.definitions.DefinitionsProvider;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RefsGenertorySelector {

    public JsonPropertyGenerator getReferredPropertyGenerator(final String definitionName) {
        return DefinitionsProvider.getJsonPropertyGenerator(definitionName);
    }
}
