package uk.gov.justice.json.generation.generators.values;

import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;

public class EnumValueGenerator implements JsonValueGenerator {

    private final RandomListItemSelector randomListItemSelector;

    private final List<Object> enums;

    public EnumValueGenerator(final Set<Object> enums) {
        this(new ArrayList(enums), new RandomListItemSelector());
    }

    public EnumValueGenerator(final List<Object> enums) {
        this(enums, new RandomListItemSelector());
    }

    @VisibleForTesting
    EnumValueGenerator(final List<Object> enums, final RandomListItemSelector randomListItemSelector) {
        this.randomListItemSelector = randomListItemSelector;
        this.enums = enums;
    }

    @Override
    public String nextValue() {

        final Object value = randomListItemSelector.selectRandomlyFrom(enums);

        if (value instanceof String) {
            return DOUBLE_QUOTE + value + DOUBLE_QUOTE;
        }

        return value.toString();
    }
}
