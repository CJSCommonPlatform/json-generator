package uk.gov.justice.json.generators.values;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.time.ZonedDateTime;
import java.util.Random;

public class RandomDateTimeGenerator {

    private final Random random = new Random();

    public String randomDateTime() {

        final int daysShift = random.nextInt(30) - 15;
        final int hoursShift = random.nextInt(24);
        final int minutesShift = random.nextInt(60);

        final ZonedDateTime now = now(UTC)
                .plusDays(daysShift)
                .plusHours(hoursShift)
                .plusMinutes(minutesShift);

        return now.format(ISO_LOCAL_DATE_TIME);
    }
}
