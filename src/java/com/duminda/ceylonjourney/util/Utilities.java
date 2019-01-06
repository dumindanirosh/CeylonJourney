package com.duminda.ceylonjourney.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This Utilities class has the methods which provides necessary functions which are useful to handle the Dates in this system.
 * @author Duminda
 */
public class Utilities {

    static Date date = new Date();

    public static Date getTime() {
        date.setTime(System.currentTimeMillis());
        return date;
    }

    public static String getTodayDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd yyyy");

        return simpleDateFormat.format(getTime());

    }
}
