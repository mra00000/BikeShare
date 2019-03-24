package com.fpt.prm.bikeshare.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringHelper {
    public static String join(String seperator, List<String> list) {
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for(String s:list) {
            sb.append(sep);
            sb.append(s);
            sep = seperator;
        }
        return sb.toString();
    }

    public static List<String> toList (String s, String seperator) {
        List<String> rs = new ArrayList<>();
        for(String s1: s.split("[" + seperator + "]")) {
            if (!s1.trim().equals("")) {
                rs.add(s1);
            }
        }
        return rs;
    }
}
