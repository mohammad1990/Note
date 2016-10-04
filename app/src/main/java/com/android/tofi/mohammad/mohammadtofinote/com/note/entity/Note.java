package com.android.tofi.mohammad.mohammadtofinote.com.note.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.android.tofi.mohammad.mohammadtofinote.R;
import com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity.Utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hamzaK on 7.5.2016.
 */
public class Note implements Parcelable {
    private int id;
    private String title;
    private Date date;
    private String noteContain;

    public Note(int id, String title, String noteContain, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.noteContain = noteContain;
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
    }

    private Note(Parcel in) {
        this.title = in.readString();
        this.date = Utility.convertSTodD(in.readString());
        this.id = in.readInt();
        this.noteContain = in.readString();
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
