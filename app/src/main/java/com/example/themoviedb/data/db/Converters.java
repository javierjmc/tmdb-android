package com.example.themoviedb.data.db;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.util.StringUtil;

import com.example.themoviedb.utils.DateTimeUtil;

import org.joda.time.DateTime;

import java.util.Collections;
import java.util.List;

/**
 * Contains {@link TypeConverter}s for the database.
 */
public class Converters {

    /**
     * Converts a comma separated list of integers to integer list.
     *
     * @param data String of comma separated integers
     * @return List of ints, empty list if input data is null.
     */
    @TypeConverter
    public List<Integer> stringToIntList(String data) {
        if (data == null)
            return Collections.emptyList();
        else
            return StringUtil.splitToIntList(data);
    }

    /**
     * Converts the given list of integers into a comma separated String.
     *
     * @param ints The list of integers.
     * @return Comma separated string composed of integers in the list. If the list is null, return
     * value is null.
     */
    @TypeConverter
    public String intListToString(List<Integer> ints) {
        return StringUtil.joinIntoString(ints);
    }

    /**
     * Converts a {@link String} to {@link DateTime}.
     *
     * @param dateTime The datetime to be converted into String.
     */
    @TypeConverter
    public String dateTimeToString(DateTime dateTime) {
        return dateTime.toString();
    }

    /**
     * Converts a {@link DateTime} to {@link String}.
     *
     * @param string The string to be converted into DateTime.
     */
    @TypeConverter
    public DateTime stringToDateTime(String string) {
        return DateTimeUtil.stringToDateTime(string);
    }
}
