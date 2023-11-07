package edu.hw5;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {
    private static final int THREE_VAL = 3;

    private static boolean isAlphabet(String s) {
        return s.matches("^[01]+$");
    }

    public static boolean isOddLength(String input) {

        return input.matches("^.(..)*$")
            && isAlphabet(input);
    }

    public static boolean startsWithZeroAndIsOddLengthOrStartsWithOneAndIsEvenLength(String input) {
        return input.matches("(0[01]{1,}|1[01]{2,})*");
        /*return input.matches("^0[01]*1$") ||
            input.matches("^1[01]*0$");*/
    }

    public static boolean hasMultipleOfThreeZeros(String input) {
        return input.replaceAll("1", "").length() % THREE_VAL == 0
            && isAlphabet(input);
    }

    public static boolean isNotElevenOrOneHundredEleven(String input) {
        return !input.matches("^(11|111)$")
            && isAlphabet(input);
    }

    public static boolean everyOddCharacterIsOne(String input) {
        return input.matches("^(1[01])*1?$")
            && isAlphabet(input);
    }

    public static boolean hasAtLeastTwoZerosAndAtMostOneOne(String input) {
        return input.matches("^(?=.*0.*0)(?!.*1.*1)[01]*$")
            && isAlphabet(input);
    }

    public static boolean hasNoConsecutiveOnes(String input) {
        return !input.matches(".*11.*")
            && isAlphabet(input);
    }
}
