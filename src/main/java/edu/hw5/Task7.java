package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {
    public static boolean hasAtLeastThreeCharactersWithThirdZero(String input) {
        Pattern pattern = Pattern.compile("^.{2}0.*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean startsAndEndsWithSameCharacter(String input) {
        Pattern pattern = Pattern.compile("^(0|1).*(\\1)$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean hasLengthBetweenOneAndThree(String input) {
        Pattern pattern = Pattern.compile("^.{1,3}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
