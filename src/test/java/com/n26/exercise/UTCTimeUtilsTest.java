package com.n26.exercise;

import com.n26.exercise.utils.UTCTimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.time.ZoneId.of;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UTCTimeUtilsTest {

    public static final String CET = "Europe/Berlin";

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionsIfDateInFuture() {
        assertTrue(UTCTimeUtils.olderThan60Seconds(now().plusSeconds(61)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionsINotUTC() {
        assertTrue(UTCTimeUtils.olderThan60Seconds(now(of(CET)).minusSeconds(61)));
    }

    @Test
    public void olderThan60SecondsIsTrueFor61SecondsOlderDate() {
        assertTrue(UTCTimeUtils.olderThan60Seconds(now(UTC).minusSeconds(61)));
    }

    @Test
    public void olderThan60SecondsIsFalseIfFutureDate() {
        assertFalse(UTCTimeUtils.olderThan60Seconds(now(UTC).plusSeconds(10)));
    }

    @Test
    public void olderThan60SecondsIsFalseFor30SecondsOlderDate() {
        assertFalse(UTCTimeUtils.olderThan60Seconds(now(UTC).minusSeconds(30)));
    }
}
