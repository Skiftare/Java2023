package edu.hw5;

import java.util.regex.Pattern;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {
    public static boolean passwordValidator(String incomePassword) {
        return Pattern.matches(".*[~!@#$%^&*|].*", incomePassword);
    }
}
