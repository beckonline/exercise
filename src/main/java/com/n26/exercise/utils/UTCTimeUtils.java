package com.n26.exercise.utils;


import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static com.google.common.base.Preconditions.checkArgument;

public class UTCTimeUtils {


    public static final ZoneOffset UTC = ZoneOffset.UTC;

    public static boolean olderThan60Seconds(ZonedDateTime dateTime) {

        return olderThanSeconds(dateTime, 60);

    }

    private static boolean olderThanSeconds(ZonedDateTime dateTime, int secondsToEvaluate) {


        Instant now = ZonedDateTime.now(UTC).toInstant();

        checkArgument(dateTime.getZone().getId().equals(UTC.getId()),"Date not in UTC.");

        Instant toCompare = dateTime.toInstant();


        Duration duration = Duration.between(toCompare,now);

        long durationInSeconds = duration.getSeconds();

        return durationInSeconds>secondsToEvaluate;

    }
}
