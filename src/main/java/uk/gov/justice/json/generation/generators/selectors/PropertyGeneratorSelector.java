package uk.gov.justice.json.generation.generators.selectors;

import uk.gov.justice.json.generation.JsonGenerationException;
import uk.gov.justice.json.generation.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.EnumPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generation.generators.properties.ObjectPropertyGenerator;
import uk.gov.justice.json.generation.generators.selectors.arrays.ArrayGeneratorSelector;

import java.util.Set;

import org.everit.json.schema.ArraySchema;
import org.everit.json.schema.CombinedSchema;
import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.ReferenceSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.StringSchema;

@SuppressWarnings("unchecked")
public class PropertyGeneratorSelector {

    private SelectorFactory selectorFactory = new SelectorFactory();
    private ArrayGeneratorSelector arrayGeneratorSelector = new ArrayGeneratorSelector();
    private StringPropertyGeneratorSelector stringPropertyGeneratorSelector = new StringPropertyGeneratorSelector();

    public JsonPropertyGenerator createGenerator(final String propertyName, final Schema schema) {

        switch (schema.getClass().getSimpleName()) {
            case "ObjectSchema":
                return new ObjectPropertyGenerator(propertyName, schema, this);
            case "ReferenceSchema":
                return createGenerator(propertyName, ((ReferenceSchema) schema).getReferredSchema());
            case "StringSchema":
                return stringPropertyGeneratorSelector.getPropertyGenerator(propertyName, (StringSchema) schema);
            case "IntegerSchema":
                return new IntegerPropertyGenerator(propertyName);
            case "BooleanSchema":
                return new BooleanPropertyGenerator(propertyName);
            case "NumberSchema":
                return new IntegerPropertyGenerator(propertyName);
            case "ArraySchema":
                return arrayGeneratorSelector.getArrayGenerator(propertyName, (ArraySchema) schema);
            case "EnumSchema":
                Set<Object> values = ((EnumSchema) schema).getPossibleValues();
                return new EnumPropertyGenerator(propertyName, values);
            case "CombinedSchema":
                return new OneOfSelector().getOneOf(propertyName, (CombinedSchema) schema);
            default:
                throw new JsonGenerationException("Unknown property type '" + schema.getClass().getSimpleName() + "'");
        }
    }
}

