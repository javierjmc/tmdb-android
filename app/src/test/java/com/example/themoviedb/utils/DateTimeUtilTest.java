package com.example.themoviedb.utils;

import org.joda.time.DateTime;
import org.junit.Test;


public class DateTimeUtilTest {

    @Test
    public void dateTimeNotNullToStringTest() {
        String dateTimeAsString = DateTimeUtil.dateTimeToString(new DateTime());
        assert (dateTimeAsString instanceof String);
    }

    @Test
    public void dateTimeNullToStringTest() {
        String dateTimeAsString = DateTimeUtil.dateTimeToString(null);
        assert (dateTimeAsString == null);
    }

    @Test
    public void stringNullToDateTimeTest() throws RuntimeException {
        DateTime stringAsDateTime = DateTimeUtil.stringToDateTime(null);
        assert (stringAsDateTime == null);
    }

    @Test
    public void stringNotNullToDateTimeBadParserTest() throws RuntimeException {
        DateTime stringAsDateTime = DateTimeUtil.stringToDateTime("2002-02-02 15:15:15");
        assert (stringAsDateTime != null);
        assert (stringAsDateTime.getYear() == 2002);
        assert (stringAsDateTime.getMonthOfYear() == 1);
        assert (stringAsDateTime.getDayOfMonth() == 2);
        assert (stringAsDateTime.getHourOfDay() == 15);
        assert (stringAsDateTime.getMinuteOfHour() == 15);
        assert (stringAsDateTime.getSecondOfMinute() == 15);
    }

    @Test
    public void stringNotNullToDateTimeGoodParserTest() throws RuntimeException {
        DateTime stringAsDateTime = DateTimeUtil.stringToDateTime("2002-02-02T15:15:15+00:00");
        assert (stringAsDateTime != null);
        assert (stringAsDateTime.getYear() == 2002);
        assert (stringAsDateTime.getMonthOfYear() == 1);
        assert (stringAsDateTime.getDayOfMonth() == 2);
        assert (stringAsDateTime.getHourOfDay() == 15);
        assert (stringAsDateTime.getMinuteOfHour() == 15);
        assert (stringAsDateTime.getSecondOfMinute() == 15);
    }
}
