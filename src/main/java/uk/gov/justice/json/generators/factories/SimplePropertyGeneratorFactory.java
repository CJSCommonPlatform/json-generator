package uk.gov.justice.json.generators.factories;

import uk.gov.justice.json.JsonGenerationException;
import uk.gov.justice.json.generators.properties.BooleanJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimeJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.JsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.RegexJsonPropertyGenerator;
import uk.gov.justice.json.generators.properties.StringJsonPropertyGenerator;

import java.util.Map;

import com.google.common.annotations.VisibleForTesting;

public class SimplePropertyGeneratorFactory {

    private final FactoryProvider factoryProvider;

    public SimplePropertyGeneratorFactory() {
        this(new FactoryProvider());
    }

    @VisibleForTesting
    SimplePropertyGeneratorFactory(final FactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    @SuppressWarnings("unchecked")
    public JsonPropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, Object> propertyDefinitions = (Map<String, Object>) value;

        final String type = (String) propertyDefinitions.get("type");
        if ("string".equals(type)) {

            final String format = (String) propertyDefinitions.get("format");
            if (format != null) {
                if ("email".equals(format)) {
                    return new EmailJsonPropertyGenerator(propertyName);
                }
                if ("date-time".equals(format)) {
                    return new IsoDateTimeJsonPropertyGenerator(propertyName);
                }
            }
            final String pattern = (String) propertyDefinitions.get("pattern");

            if (pattern != null) {
                return new RegexJsonPropertyGenerator(propertyName, pattern);
            }

            return new StringJsonPropertyGenerator(propertyName);
        }

        if ("integer".equals(type)) {
            return new IntegerJsonPropertyGenerator(propertyName);
        }
        if ("boolean".equals(type)) {
            return new BooleanJsonPropertyGenerator(propertyName);
        }

        if("object".equals(type)) {
            final Object properties = propertyDefinitions.get("properties");
            return factoryProvider.createNewObjectFactory().createGenerator(propertyName, properties);
        }

        throw new JsonGenerationException("Unknown property type '" + type + "'");
    }
}
