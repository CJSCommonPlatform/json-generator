package uk.gov.justice.json.generators.values;

import java.util.List;
import java.util.Random;

public class RandomListItemSelector {

    private Random random = new Random();

    public Object selectRandomlyFrom(final List<Object> values) {
        final int index = random.nextInt(values.size());
        return values.get(index);
    }
}
