package com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.tofi.mohammad.mohammadtofinote.com.note.Database.NoteContract;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hamzaK on 7.5.2016.
 */
public class Utility {
    public static void storeNote(Context context, Note note) {
        ContentValues coValues = new ContentValues();
        String noteTitle = note.getTitle();
        String noteDescription = note.getNoteContain();
        String noteDate = Utility.getCurrentlyDate();
        coValues.put(NoteContract.NoteEntry.COLUMN_TITLE, noteTitle);
        coValues.put(NoteContract.NoteEntry.COLUMN_CONTAIN, noteDescription);
        coValues.put(NoteContract.NoteEntry.COLUMN_DATE, noteDate);
        context.getContentResolver().insert(NoteContract.NoteEntry.CONTENT_URI, coValues);
    }

    public static void updateNote(Context context, Note note) {
        ContentValues coValues = new ContentValues();
        String noteTitle = note.getTitle();
        String noteDescription = note.getNoteContain();
        Date noteDate = note.getDate();
        coValues.put(NoteContract.NoteEntry.COLUMN_TITLE, noteTitle);
        coValues.put(NoteContract.NoteEntry.COLUMN_CONTAIN, noteDescription);
        coValues.put(NoteContract.NoteEntry.COLUMN_DATE, convertDToS(noteDate));
        context.getContentResolver().update(NoteContract.NoteEntry.CONTENT_URI,
                coValues, NoteContract.NoteEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public static List<Note> getNotes(Context context) {
        List<Note> notes = new ArrayList<>();
        if (context != null) {
            Cursor c = context.getContentResolver().query(NoteContract.NoteEntry.CONTENT_URI,
                    null,
                    null, null,
                    NoteContract.NoteEntry.COLUMN_DATE);
            if(c!=null) {
                if (c.moveToFirst()) {
                    do {
                        notes.add(new Note(c.getColumnIndex(NoteContract.NoteEntry.COLUMN_ID), c.getString(c.getColumnIndex(NoteContract.NoteEntry.COLUMN_TITLE)),
                                c.getString(c.getColumnIndex(NoteContract.NoteEntry.COLUMN_CONTAIN)), convertSTodD(c.getString(c.getColumnIndex(NoteContract.NoteEntry.COLUMN_DATE)))));
                    } while (c.moveToNext());
                }
                c.close();
            }
        }
        return notes;
    }

    public static Date convertSTodD(String stringDate) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDToS(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = dateFormat.format(date);
        return datetime;
    }

    public static String getCurrentlyDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = dateFormat.format(date);
        return datetime;
    }
}
