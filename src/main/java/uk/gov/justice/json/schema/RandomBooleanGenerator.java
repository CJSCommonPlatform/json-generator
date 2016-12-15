package uk.gov.justice.json.schema;

import java.util.Random;

public class RandomBooleanGenerator {

    private final Random random = new Random();

    public boolean randomBoolean() {
       return random.nextBoolean();
    }
}
