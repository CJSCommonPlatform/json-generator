package uk.gov.justice.json.generator.value.string;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static uk.gov.justice.json.generation.Constants.DOUBLE_QUOTE;

import uk.gov.justice.services.test.utils.core.random.Generator;

import java.time.ZonedDateTime;
import java.util.Random;


public class IsoDateTimeGenerator extends Generator<String> {

    @Override
    public String next() {

        final int daysShift = RANDOM.nextInt(30) - 15;
        final int hoursShift = RANDOM.nextInt(24);
        final int minutesShift = RANDOM.nextInt(60);

        final ZonedDateTime now = now(UTC)
                .plusDays(daysShift)
                .plusHours(hoursShift)
                .plusMinutes(minutesShift);

        return now.format(ISO_LOCAL_DATE_TIME);
    }
}
