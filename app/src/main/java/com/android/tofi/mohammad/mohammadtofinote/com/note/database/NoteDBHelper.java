package com.android.tofi.mohammad.mohammadtofinote.com.note.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.entity.Note;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hamzaK on 7.5.2016.
 */
public class NoteDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "note.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTAIN = "contain";
    public static final String COLUMN_DATE = "date";
    public static final String CREATE_QUERY = "create table "
            + TABLE_NAME + " (" + COLUMN_ID + " integer primary key not null, "
            + COLUMN_TITLE + " text not null,"
            + COLUMN_CONTAIN + " text not null,"
            + COLUMN_DATE + " date not null)";

    public NoteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    public boolean insertContact(String title, String noteContain, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_CONTAIN, noteContain);
        contentValues.put(COLUMN_DATE, date);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from note where _id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(int id, String title, String noteContain, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_CONTAIN, noteContain);
        contentValues.put(COLUMN_DATE, date);
        db.update(TABLE_NAME, contentValues, "_id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public int deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "_id = ? ",
                new String[]{Integer.toString(id)});

    }

    public ArrayList<Note> getAllNote() {
        ArrayList<Note> array_list = new ArrayList<Note>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME +" ORDER BY _id DESC", null);


        res.moveToFirst();


        while (res != null && res.isAfterLast() == false) {
            array_list.add(new Note(Integer.valueOf(res.getString(res.getColumnIndex(COLUMN_ID))),
                    res.getString(res.getColumnIndex(COLUMN_TITLE)), res.getString(res.getColumnIndex(COLUMN_CONTAIN)),
                    Utility.convertSTodD(res.getString(res.getColumnIndex(COLUMN_DATE)))));
            res.moveToNext();
        }
        return array_list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);

    }
}
