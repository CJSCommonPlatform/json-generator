package uk.gov.justice.json.schema;

public class RandomEmailGenerator {

    private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    public String randomEmail() {
        return randomStringGenerator.randomString() + "@test.com";
    }
}
