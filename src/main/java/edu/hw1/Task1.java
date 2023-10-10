package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
class Task1 {
    private static final Integer FROM_MINUTES_TO_SECONDS = 60;

    static int minutesToSeconds(@NotNull String s) {
        int seconds;
        int minites;
        String timeInput = s.replace(" ", "");
        String[] minsAndSecs = timeInput.split(":");
        if (minsAndSecs.length != 2) {
            return -1;
        }

        try {
            minites = Integer.parseInt(minsAndSecs[0]);
        } catch (NumberFormatException e) {
            minites = -1;
        }
        try {
            seconds = Integer.parseInt(minsAndSecs[1]);
        } catch (NumberFormatException e) {
            seconds = -1;
        }

        int ans = minites * FROM_MINUTES_TO_SECONDS + seconds;
        if (seconds > FROM_MINUTES_TO_SECONDS || seconds < 0 || minites < 0) {
            return -1;
        }

        return ans;
    }

}
