package uk.gov.justice.json.generators.values;

import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;

public class RandomEnumValueGenerator implements JsonValueGenerator {

    private final RandomListItemSelector randomListItemSelector;

    private final List<Object> enums;

    public RandomEnumValueGenerator(final List<Object> enums) {
        this(enums, new RandomListItemSelector());
    }

    @VisibleForTesting
    RandomEnumValueGenerator(final List<Object> enums, final RandomListItemSelector randomListItemSelector) {
        this.randomListItemSelector = randomListItemSelector;
        this.enums = enums;
    }

    @Override
    public String nextValue() {

        final Object value = randomListItemSelector.selectRandomlyFrom(enums);

        if(value instanceof String) {
            return DOUBLE_QUOTE + value + DOUBLE_QUOTE;
        }

        return value.toString();
    }
}
