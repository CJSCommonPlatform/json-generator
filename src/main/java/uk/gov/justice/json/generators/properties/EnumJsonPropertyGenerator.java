package uk.gov.justice.json.generators.properties;

import uk.gov.justice.json.formatting.QuotedJsonPropertyFormatter;
import uk.gov.justice.json.formatting.UnquotedJsonPropertyFormatter;
import uk.gov.justice.json.generators.values.RandomListItemGenerator;

import java.util.List;

public class EnumJsonPropertyGenerator implements JsonPropertyGenerator {

    private final String name;
    private final List<Object> enums;

    private RandomListItemGenerator randomListItemGenerator = new RandomListItemGenerator();
    private QuotedJsonPropertyFormatter quotedJsonPropertyFormatter = new QuotedJsonPropertyFormatter();
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

        final Object randomValue = randomListItemGenerator.selectRandomlyFrom(enums);
        if (randomValue instanceof String) {
            return quotedJsonPropertyFormatter.toJson(name, randomValue.toString());
        }

        return unquotedJsonPropertyFormatter.toJson(name, randomValue);
    }
}
