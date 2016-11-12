package com.android.tofi.mohammad.mohammadtofinote.com.note.Database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by aliT on 11/12/2016.
 */

public class NoteContract {
    public static final String CONTENT_AUTHORITY = "com.android.tofi.mohammad.mohammadtofinote";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_NOTE = "note";

    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    public static final class NoteEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTE;
        // Table name
        public static final String TABLE_NAME = "note";


        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTAIN = "contain";
        public static final String COLUMN_DATE = "date";

        public static Uri buildNoteUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
