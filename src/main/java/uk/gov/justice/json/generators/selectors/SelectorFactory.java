package uk.gov.justice.json.generators.selectors;

import uk.gov.justice.json.generators.selectors.arrays.ListArrayGeneratorSelector;
import uk.gov.justice.json.generators.selectors.arrays.TupleArrayGeneratorSelector;
import uk.gov.justice.json.generators.selectors.arrays.UnspecifiedArrayGeneratorSelector;

public class SelectorFactory {

    public ObjectGeneratorSelector createNewObjectGeneratorSelector() {
        return new ObjectGeneratorSelector();
    }

    public PropertyGeneratorSelector createNewPropertyGeneratorSelector() {
        return new PropertyGeneratorSelector();
    }

    public ListArrayGeneratorSelector createNewListArrayGeneratorSelector() {
        return new ListArrayGeneratorSelector();
    }

    public TupleArrayGeneratorSelector createNewTupleArrayGeneratorSelector() {
        return new TupleArrayGeneratorSelector();
    }

    public UnspecifiedArrayGeneratorSelector createNewUnspecifiedArrayGeneratorSelector() {
        return new UnspecifiedArrayGeneratorSelector();
    }
}
