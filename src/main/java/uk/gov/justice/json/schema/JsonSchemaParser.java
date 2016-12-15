package uk.gov.justice.json.schema;

import static java.util.stream.Collectors.toList;

import uk.gov.justice.json.generators.PropertyGenerator;
import uk.gov.justice.json.generators.properties.BooleanPropertyGenerator;
import uk.gov.justice.json.generators.properties.EmailPropertyGenerator;
import uk.gov.justice.json.generators.properties.IntegerPropertyGenerator;
import uk.gov.justice.json.generators.properties.IsoDateTimePropertyGenerator;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JsonSchemaParser {

    @SuppressWarnings("unchecked")
    public JsonDocumentGenerator parse(final String schema) {

        final Map schemaMap = new Gson().fromJson(schema, Map.class);

        final Map<String, Object> properties = (Map<String, Object>) schemaMap.get("properties");

        final List<PropertyGenerator> propertyGenerators = properties
                .keySet()
                .stream()
                .map(propertyName -> createGenerator(propertyName, properties.get(propertyName)))
                .collect(toList());

        return new JsonDocumentGenerator(propertyGenerators);
    }

    @SuppressWarnings("unchecked")
    private PropertyGenerator createGenerator(final String propertyName, final Object value) {

        final Map<String, String> propertyDefinitions = (Map<String, String>) value;

        if ("string".equals(propertyDefinitions.get("type"))) {

            final String format = propertyDefinitions.get("format");
            if (format != null) {
                if ("email".equals(format)) {
                    return new EmailPropertyGenerator(propertyName);
                }
                if ("date-time".equals(format)) {
                    return new IsoDateTimePropertyGenerator(propertyName);
                }
            }
            final String pattern = propertyDefinitions.get("pattern");

            if (pattern != null) {
                return new RegexPropertyGenerator(propertyName, pattern);
            }

            return new StringPropertyGenerator(propertyName);
        }

        if ("integer".equals(propertyDefinitions.get("type"))) {
            return new IntegerPropertyGenerator(propertyName);
        }
        if ("boolean".equals(propertyDefinitions.get("type"))) {
            return new BooleanPropertyGenerator(propertyName);
        }

        return null;
    }
}
