package edu.hw5;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task7 {
    public static boolean hasAtLeastThreeCharactersWithThirdZero(String input) {
        return input.matches("[01]{3}0.*");
    }

    public static boolean startsAndEndsWithSameCharacter(String input) {
        return input.matches("^(0|1).*(\\1)$");
    }

    public static boolean hasLengthBetweenOneAndThree(String input) {
        return input.matches("[01]{1,3}");
    }
}
