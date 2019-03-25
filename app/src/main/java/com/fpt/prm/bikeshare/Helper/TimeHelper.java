package com.fpt.prm.bikeshare.Helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeHelper {
    public static String getTime(Timestamp timestamp){

            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            TimeZone tz = TimeZone.getDefault();
            dateFormat.setTimeZone(tz);
            Date firstParsedDate = null;
            long diff = 0;
            try {
                firstParsedDate = dateFormat.parse(String.valueOf(timestamp));
                Date secondParsedDate = dateFormat.parse(dateFormat.format(new Date()));
                diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                System.out.println(firstParsedDate);
                System.out.println(secondParsedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long diffmin = diff / (60 * 1000);
            System.out.println("a:" + diff);
            System.out.println("b:" + diffmin);
            String str = "";
            if (diffmin == 0){
                str = "just now";
            } else
            if (diffmin < 60) {
                str = diffmin + "' ago";
            } else if (diffmin >= 60 && diffmin < (60 * 24)) {
                int mod = (int) diffmin % 60;
                str = (diffmin / 60) + "h ago";

            } else {
                str = diffmin / (60 * 24) + "d ago";
            }
            return str;

    }

}
