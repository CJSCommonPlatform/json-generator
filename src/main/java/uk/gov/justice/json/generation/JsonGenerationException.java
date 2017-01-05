package uk.gov.justice.json.generation;

public class JsonGenerationException extends RuntimeException {

     public JsonGenerationException(final String message) {
         super(message);
     }

     public JsonGenerationException(final String message, final Throwable cause) {
         super(message, cause);
     }
}
