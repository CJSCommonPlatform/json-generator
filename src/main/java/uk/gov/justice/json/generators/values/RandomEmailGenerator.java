package uk.gov.justice.json.generators.values;

public class RandomEmailGenerator {

    private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    public String randomEmail() {
        return randomStringGenerator.randomString() + "@test.com";
    }
}
