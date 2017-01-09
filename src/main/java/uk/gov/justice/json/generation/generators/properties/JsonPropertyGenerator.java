package uk.gov.justice.json.generation.generators.properties;

import uk.gov.justice.json.generation.generators.values.JsonValueGenerator;

public interface JsonPropertyGenerator {

    String getName();
    String nextJson();

}
