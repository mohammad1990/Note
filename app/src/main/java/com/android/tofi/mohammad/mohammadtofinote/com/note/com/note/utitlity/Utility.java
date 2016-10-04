package com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hamzaK on 7.5.2016.
 */
public class Utility {
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
    public  static  String getCurrentlyDate()
    {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = dateFormat.format(date);
        return datetime;
    }
}
