package com.yangaiche.yackeeper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ui on 16/4/25.
 */
public class TimeUtils {
    public static String[] getDateFromUTC(String utcTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            sdf.parse(utcTime);
            Calendar c = sdf.getCalendar();
            StringBuffer buffer1 = new StringBuffer();
            String date = buffer1.append(c.get(Calendar.YEAR)).append("/").append(c.get(Calendar.MONTH) + 1).append("/").append(c.get(Calendar.DAY_OF_MONTH)).toString();
            StringBuffer buffer = new StringBuffer();
            String time = buffer.append(c.get(Calendar.HOUR_OF_DAY)).append(":").append(c.get(Calendar.MINUTE)).toString();
            return new String[]{date, time};
        } catch (ParseException pe) {
            System.out.println("Error offset: " + pe.getErrorOffset());
            pe.printStackTrace();
        }
        return null;
    }
}
