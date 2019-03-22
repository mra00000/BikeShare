/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahcl
 */
public class MyStringHelpers {
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
