package com.android.tofi.mohammad.mohammadtofinote.com.note.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hamzaK on 7.5.2016.
 */
public class NoteDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "note.db";
    public static final int DATABASE_VERSION = 1;


    public NoteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_QUERY = "create table "
                + NoteContract.NoteEntry.TABLE_NAME + " ("
                + NoteContract.NoteEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + NoteContract.NoteEntry.COLUMN_TITLE + " text,"
                + NoteContract.NoteEntry.COLUMN_CONTAIN + " text,"
                + NoteContract.NoteEntry.COLUMN_DATE + " date not null,"
                + NoteContract.NoteEntry.COLUMN_COLOR + " INTEGER" +
                ")";

        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + NoteContract.NoteEntry.TABLE_NAME);
        onCreate(db);

    }
}
