package com.example.themoviedb.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Assists getting a file as a {@link String} from the assets.
 */
public class RestServiceTestHelper {

    /**
     * Gets a {@link String} from a given filePath.
     *
     * @param context  Android context to get assets from.
     * @param filePath the path where the file is located.
     * @return File as a {@link String}.
     */
    public static String getStringFromFile(final Context context, final String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);
        final String streamString = streamToString(stream);
        stream.close();

        return streamString;
    }

    /**
     * Converts the given {@link InputStream} to a String.
     *
     * @param inputStream the stream to be converted.
     * @return {@link String} containing the stream.
     */
    private static String streamToString(final InputStream inputStream) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final StringBuilder stringBuilder = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }

        reader.close();

        return stringBuilder.toString();
    }
}
