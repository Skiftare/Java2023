package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {

    public static boolean isSubsequence(String s, String t) {
        String pattern = ".*" + Pattern.quote(s) + ".*";
        return Pattern.matches(pattern, t);
    }

}
