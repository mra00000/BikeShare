package com.fpt.prm.bikeshare.Helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
    public static String getTime(Timestamp timestamp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss S");
        Date firstParsedDate = null;
        try {
            firstParsedDate = dateFormat.parse(String.valueOf(timestamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date secondParsedDate = new Date();
        long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
        String str = String.valueOf(diff);
        return str;
    }

}
