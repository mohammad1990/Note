package com.android.tofi.mohammad.mohammadtofinote.com.note.Entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity.Utility;

import java.util.Date;

/**
 * Created by hamzaK on 7.5.2016.
 */
public class Note implements Parcelable {
    private int id;
    private String title;
    private Date date;
    private String noteContain;
    private int noteColor;

    public Note(int id, String title, String noteContain, Date date, int noteColor) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.noteContain = noteContain;
        this.noteColor = noteColor;
    }

    public Note(String title, String noteContain, Date date, int noteColor) {
        this.title = title;
        this.date = date;
        this.noteContain = noteContain;
        this.noteColor = noteColor;
    }


    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteContain() {
        return noteContain;
    }

    public void setNoteContain(String noteContain) {
        this.noteContain = noteContain;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(Utility.convertDToS(date));
        dest.writeInt(id);
        dest.writeString(noteContain);
        dest.writeInt(noteColor);
    }

    private Note(Parcel in) {
        this.title = in.readString();
        this.date = Utility.convertSTodD(in.readString());
        this.id = in.readInt();
        this.noteContain = in.readString();
        this.noteColor = in.readInt();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {

        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
