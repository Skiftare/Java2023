package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {
    public static @NotNull String fixString(@NotNull String brokenString) {
        StringBuilder fixedString = new StringBuilder();

        for (int i = 0; i < brokenString.length() - (brokenString.length() % 2); i += 2) {
            fixedString.append(brokenString.charAt(i + 1));
            fixedString.append(brokenString.charAt(i));
        }

        if (brokenString.length() % 2 == 1) {
            fixedString.append(brokenString.charAt(brokenString.length() - 1));
        }

        return fixedString.toString();
    }
}
