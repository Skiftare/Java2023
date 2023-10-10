package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
class Task1 {
    private static final Integer FROM_MINS_TO_SECONDS = 60;

    static int minutesToSeconds(@NotNull String s) {
        int secs;
        int mins;
        String timeInput = s.replace(" ", "");
        String[] minsAndSecs = timeInput.split(":");
        if (minsAndSecs.length != 2) {
            return -1;
        }

        try {
            mins = Integer.parseInt(minsAndSecs[0]);
        } catch (NumberFormatException e) {
            mins = -1;
        }
        try {
            secs = Integer.parseInt(minsAndSecs[1]);
        } catch (NumberFormatException e) {
            secs = -1;
        }
        int ans = mins * FROM_MINS_TO_SECONDS + secs;
        if (secs > FROM_MINS_TO_SECONDS || secs < 0 || mins < 0) {
            return -1;
        }

        return ans;
    }

}
