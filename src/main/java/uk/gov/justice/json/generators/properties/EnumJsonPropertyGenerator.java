package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomListItemSelector;

import java.util.List;

public class EnumJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<Object> enums;

    private RandomListItemSelector randomListItemSelector = new RandomListItemSelector();
    private UnquotedJsonPropertyFormatter unquotedJsonPropertyFormatter = new UnquotedJsonPropertyFormatter();

    public EnumJsonPropertyGenerator(
            final String name,
            final List<Object> enums) {
        this.name = name;
        this.enums = enums;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String nextJson() {

        final Object randomValue = randomListItemSelector.selectRandomlyFrom(enums);


        return unquotedJsonPropertyFormatter.toJson(name, randomValue);
    }
}
