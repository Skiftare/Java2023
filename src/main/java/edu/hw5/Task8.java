package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    public static boolean isOddLength(String string) {
        Pattern pattern = Pattern.compile("^(.{2})*.$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean startsWithZeroAndHasOddLengthOrStartsWithOneAndHasEvenLength(String string) {
        Pattern pattern = Pattern.compile("^(0.{2})*0$|^(1.{2})*1$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean hasMultipleOfThreeZeros(String string) {
        Pattern pattern = Pattern.compile("^([^1]*0[^1]*){3}[^1]*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean isNot111Or11(String string) {
        Pattern pattern = Pattern.compile("^(?!11+$)^(?!111+$).*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean everyOddCharacterIsOne(String string) {
        Pattern pattern = Pattern.compile("^((.{2}1)*1)?$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean hasAtLeastTwoZerosAndAtMostOneOne(String string) {
        Pattern pattern = Pattern.compile("^([^1]*0[^1]*){2,}([^1]*1[^1]*)?$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean hasNoConsecutiveOnes(String string) {
        Pattern pattern = Pattern.compile("^(?!.*11).*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
