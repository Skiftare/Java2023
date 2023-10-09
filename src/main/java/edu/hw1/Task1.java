package edu.hw1;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
class Task1 {
    private static final Integer SIXTY_NUM = 60;

    static int minutesToSeconds(@NotNull String s) {
        int secs = 0;
        int mins = 0;
        String[] nums = s.split(":");
        if (nums.length != 2) {
            return -1;
        }

        try {
            mins = Integer.parseInt(nums[0]);
        } catch (NumberFormatException e) {
            mins = -1;
        }
        try {
            secs = Integer.parseInt(nums[1]);
        } catch (NumberFormatException e) {
            secs = -1;
        }
        int ans = mins * SIXTY_NUM + secs;
        if (secs > SIXTY_NUM || secs < 0 || mins < 0) {
            return -1;
        }

        return ans;
    }

}
