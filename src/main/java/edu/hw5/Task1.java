package edu.hw5;

import java.time.Duration;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {
    private static final int MAX_MIN = 60;

    private static boolean checkOfCorrectsValues(int[] mas) {
        for (var it:mas) {
            if (it < 0 || it > MAX_MIN) {
                return false;
            }
        }
        return true;
    }

    private static Duration calculateDurationForString(@NotNull String input) {
        String[] parts = input.split(" - ");
        String start = parts[0];
        String end = parts[1];

        String[] startTime = start.split(", ")[1].split(":");
        String[] endTime = end.split(", ")[1].split(":");

        int startHour = Integer.parseInt(startTime[0]);
        int startMinute = Integer.parseInt(startTime[1]);
        int endHour = Integer.parseInt(endTime[0]);
        int endMinute = Integer.parseInt(endTime[1]);

        if (!checkOfCorrectsValues(new int[]{startHour, startMinute, endHour, endMinute})) {
            throw new RuntimeException("Wrong time format");
        }

        Duration intervalDuration = Duration.ofHours(endHour - startHour)
            .plusMinutes(endMinute - startMinute);
        return intervalDuration;
    }

    public static String computerClubAnalytics(@NotNull List<String> input) {
        Duration totalDuration = Duration.ZERO;

        for (String interval : input) {
            totalDuration = totalDuration.plus(calculateDurationForString(interval));
        }

        Long hours = totalDuration.toHours();
        Long minutes = totalDuration.toMinutes() % MAX_MIN;
        String res = hours + "ч " + minutes + "м";
        return res;
    }
}
