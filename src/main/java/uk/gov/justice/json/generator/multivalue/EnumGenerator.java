package uk.gov.justice.json.generator.multivalue;

import uk.gov.justice.json.generation.generators.values.RandomListItemSelector;
import uk.gov.justice.json.generator.value.PrimitiveGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;

public class EnumGenerator implements PrimitiveGenerator<Enum> {

    private final RandomListItemSelector randomListItemSelector;

    private final List<Object> enums;

    public EnumGenerator(final Set<Object> enums) {
        this(new ArrayList(enums), new RandomListItemSelector());
    }

    public EnumGenerator(final List<Object> enums) {
        this(enums, new RandomListItemSelector());
    }

    @VisibleForTesting
    EnumGenerator(final List<Object> enums, final RandomListItemSelector randomListItemSelector) {
        this.randomListItemSelector = randomListItemSelector;
        this.enums = enums;
    }

    @Override
    public Enum next() {
        return (Enum) randomListItemSelector.selectRandomlyFrom(enums);
    }
}
