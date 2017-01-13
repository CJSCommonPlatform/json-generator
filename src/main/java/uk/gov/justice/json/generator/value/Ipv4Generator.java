package uk.gov.justice.json.generator.value;


import java.util.regex.Pattern;

public class Ipv4Generator implements StringGenerator{
    public static final String pattern = "((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\n";

    @Override
    public java.lang.String next() {
        return new RegexGenerator(Pattern.compile(pattern)).next();
    }
}
