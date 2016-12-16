package uk.gov.justice.json.generators.values;

import java.util.List;
import java.util.Random;

public class RandomListItemGenerator {

    private Random random = new Random();

    public Object selectRandomlyFrom(List<Object> values) {
        final int index = random.nextInt(values.size());
        return values.get(index);
    }
}
