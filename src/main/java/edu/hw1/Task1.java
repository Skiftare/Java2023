package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
class Task1 {
    private static final Integer TEN_MULTI = 10;
    private static final Integer SIXTY_NUM = 60;

    static int minutesToSeconds(@NotNull String s) {
        int secs = 0;
        boolean flag = false;
        int mins = 0;


        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ':') {
                flag = true;
                continue;
            }
            if (s.charAt(i) == '-') {
                return -1;
            }
            if (!flag) {
                mins = mins * TEN_MULTI + (s.charAt(i) - '0');
            } else {
                secs = secs * TEN_MULTI + (s.charAt(i) - '0');
            }

        }
        int ans = mins * SIXTY_NUM + secs;
        if (secs > SIXTY_NUM || !flag) {
            return -1;
        }

        return ans;
    }

}
