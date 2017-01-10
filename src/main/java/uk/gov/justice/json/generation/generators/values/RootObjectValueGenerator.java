package uk.gov.justice.json.generation.generators.values;

import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import uk.gov.justice.json.generation.generators.values.JsonValueGenerator;

import com.mifmif.common.regex.Generex;

public class RootObjectValueGenerator implements JsonValueGenerator{
    @Override
    public String nextValue() {
        return "-";
    }
}
