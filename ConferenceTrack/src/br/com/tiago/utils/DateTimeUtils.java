package br.com.tiago.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tiago_de_Oliveira on 9/19/2014.
 */
public class DateTimeUtils {
    public static Long sumTime(String hour, Integer minutes) {
        Long iHour = hoursInMillis(hour);
        iHour += minutesInMillis(minutes);
        return iHour;
    }

    public static Long minutesInMillis(Integer minutes) {
        return Long.valueOf((minutes * 60) * 1000);
    }

    public static Long hoursInMillis(String hour) {
        Long result = Long.valueOf(0);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date d = df.parse(hour);
            result = d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String millisInHours(Long milis) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(new Date(milis));
    }

}
