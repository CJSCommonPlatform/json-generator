package uk.gov.justice.json.generators.values;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static uk.gov.justice.json.Constants.DOUBLE_QUOTE;

import java.time.ZonedDateTime;
import java.util.Random;

public class DateTimeValueGenerator implements JsonValueGenerator {

    private final Random random = new Random();

    @Override
    public String nextValue() {

        final int daysShift = random.nextInt(30) - 15;
        final int hoursShift = random.nextInt(24);
        final int minutesShift = random.nextInt(60);

        final ZonedDateTime now = now(UTC)
                .plusDays(daysShift)
                .plusHours(hoursShift)
                .plusMinutes(minutesShift);

        return DOUBLE_QUOTE + now.format(ISO_LOCAL_DATE_TIME) + DOUBLE_QUOTE;
    }
}