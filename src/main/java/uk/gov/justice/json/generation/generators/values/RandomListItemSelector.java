package uk.gov.justice.json.generation.generators.values;

import org.everit.json.schema.CombinedSchema;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.ObjectSchema;
import org.everit.json.schema.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomListItemSelector {

    private Random random = new Random();


    public Object selectRandomlyFrom(final List<Object> values) {
        final int index = random.nextInt(values.size());
        return  values.get(index);
    }
    public Object selectRandomlyFrom1(final List<Schema> values) {
        final int index = random.nextInt(values.size());
        return  values.get(index);
    }

    public ObjectSchema selectRandomlyFrom(final CombinedSchema combinedSchema) {
        final int index = random.nextInt(combinedSchema.getSubschemas().size());
        return  (ObjectSchema) combinedSchema.getSubschemas().toArray()[index];
    }
}
