package com.example.themoviedb.utils;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    public static final DateTimeFormatter DATETIME_PARSER = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
    public static final DateTimeFormatter DATETIME_PRINTER = ISODateTimeFormat.dateTime();

    private static final DateTimeFormatter BAD_DATE_PARSER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static DateTime stringToDateTime(final String dateString) {
        if (dateString != null && dateString.length() != 0) {
            try {
                return DATETIME_PARSER.parseDateTime(dateString);
            } catch (UnsupportedOperationException var2) {
                logger.debug("Parsing unsupported: " + dateString, var2);
            } catch (IllegalArgumentException var3) {
                logger.debug("Datestring [" + dateString + "] is invalid", var3);
            }

            return BAD_DATE_PARSER.parseDateTime(dateString);
        } else {
            return null;
        }
    }

    public static String dateTimeToString(final DateTime dateTime) {
        return dateTime == null ? null : DATETIME_PRINTER.print(dateTime);
    }
}

