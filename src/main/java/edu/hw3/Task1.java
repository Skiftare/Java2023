package edu.hw3;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static char getReverse(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ('z' - (c - 'a'));
        } else {
            return (char) ('Z' - (c - 'A'));
        }

    }

    static @NotNull String atbash(@NotNull String message) {
        StringBuilder res = new StringBuilder();
        char currentChar = ' ';
        for (int i = 0; i < message.length(); i++) {
            currentChar = message.charAt(i);
            if (isLetter(currentChar)) {
                res.append(getReverse(currentChar));
            } else {
                res.append(currentChar);
            }
        }
        return res.toString();

    }
}
