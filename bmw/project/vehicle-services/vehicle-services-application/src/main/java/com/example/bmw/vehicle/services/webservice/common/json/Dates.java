package com.example.bmw.vehicle.services.webservice.common.json;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalQuery;

/**
 * I provide date and time utilities.
 *
 * @author Lars Gielsok, MaibornWolff
 */
public final class Dates {

    private Dates() {
    }


    /**
     * Returns a converter from a temporal to UTC.
     *
     * @return a converter from a temporal to UTC.
     */
    public static TemporalQuery<OffsetDateTime> temporalToUTCDateTimeConverter() {
        return dateTime -> OffsetDateTime.from(dateTime).withOffsetSameInstant(ZoneOffset.UTC);
    }


    /**
     * Returns a timestamp with the same date and time as the given {@link OffsetDateTime}.
     * The timezone-offset is NOT taken into account.
     *
     * @param dateTime the {@link OffsetDateTime}.
     * @return the timestamp.
     */
    public static Timestamp asSqlTimestampIgnoreTimezone(final OffsetDateTime dateTime) {
        return dateTime == null ? null : Timestamp.valueOf(dateTime.toLocalDateTime());
    }


    /**
     * Returns a time with UTC offset for a given timestamp.
     *
     * @param timestamp the timestamp to convert.
     * @return a time with UTC offset for a given timestamp.
     */
    public static OffsetDateTime asOffsetDateTimeUtc(final Timestamp timestamp) {
        return timestamp == null ? null : createDateTimeWithOffsetUTC(timestamp);
    }


    private static OffsetDateTime createDateTimeWithOffsetUTC(final Timestamp timestamp) {
        final Instant instant = timestamp.toInstant();
        final OffsetDateTime dateTimeWithLocalOffset = OffsetDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        return dateTimeWithLocalOffset.withOffsetSameLocal(ZoneOffset.UTC);
    }
}
