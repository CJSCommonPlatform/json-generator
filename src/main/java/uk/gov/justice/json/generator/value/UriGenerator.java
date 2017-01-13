package uk.gov.justice.json.generator.value;

public class UriGenerator implements StringGenerator{

    private final
    uk.gov.justice.services.test.utils.core.random.UriGenerator generator =
            new uk.gov.justice.services.test.utils.core.random.UriGenerator();

    @Override
    public String next() {
        return generator.next().toString();
    }
}
