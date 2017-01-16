package uk.gov.justice.json.generator.value.string;

import uk.gov.justice.services.test.utils.core.random.Generator;

public class UriGenerator extends Generator<String> {

    private final uk.gov.justice.services.test.utils.core.random.UriGenerator generator =
            new uk.gov.justice.services.test.utils.core.random.UriGenerator();

    @Override
    public String next() {
        return generator.next().toString();
    }
}
