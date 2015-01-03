package com.alisovenko.jdk.datetime;

import org.junit.*;

import java.time.*;

/**
 * <p/>
 * alisovenko 12.04.14
 */
public class NewDateTimeTest {
    @Test
    public void test() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        ZonedDateTime ztime = ZonedDateTime.now();
        System.out.println(ztime);
        OffsetDateTime otime = OffsetDateTime.now();
        System.out.println(otime);

        ZoneOffset offset = ZoneOffset.of("+03:00");

        OffsetTime offsetTime = OffsetTime.now();
        System.out.printf("Now: %s\n", offsetTime);

        OffsetTime offsetTime1 = offsetTime.withOffsetSameInstant(offset);
        System.out.printf("with offset: %s\n", offsetTime1);

        OffsetTime offsetTime2 = offsetTime.withOffsetSameLocal(offset);
        System.out.printf("with offset local: %s\n", offsetTime2);
    }
}
