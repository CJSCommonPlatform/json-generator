package uk.gov.justice.json.generator.value.string;

import static uk.gov.justice.services.common.converter.ZonedDateTimes.fromSqlTimestamp;

import uk.gov.justice.services.common.converter.ZonedDateTimes;
import uk.gov.justice.services.test.utils.core.random.Generator;

import java.sql.Timestamp;


public class IsoDateTimeGenerator extends Generator<String> {

    private static final long YEARS_RANGE_FORWARD = 10L;
    private static final long START_DATE = 0L;
    private static final long MILLIS_IN_YEAR = 1471228928L;

    @Override
    public String next(){
        long ms = START_DATE + (Math.abs(RANDOM.nextLong()) % (YEARS_RANGE_FORWARD * MILLIS_IN_YEAR));
        return ZonedDateTimes.toString(fromSqlTimestamp(new Timestamp(ms)));
    }
}
