package com.example.themoviedb.data.domain.moshi;

import com.example.themoviedb.utils.DateTimeUtil;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Moshi converter methods for JodaTime classes
 */
public class JodaTimeMoshiAdapter {

    @FromJson
    DateTime dateTimeFromJson(String json) {
        return DateTimeUtil.stringToDateTime(json);
    }

    @ToJson
    String dateTimeToJson(DateTime dateTime) {
        return DateTimeUtil.dateTimeToString(dateTime);
    }

    @FromJson
    LocalDate localDateFromJson(String json) {
        if (json == null || json.isEmpty()) return null;

        return LocalDate.parse(json);
    }

    @ToJson
    String localDateToJson(LocalDate item) {
        return item.toString();
    }

}
