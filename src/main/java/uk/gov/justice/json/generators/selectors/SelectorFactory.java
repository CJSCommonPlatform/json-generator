package uk.gov.justice.json.generators.selectors;

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
