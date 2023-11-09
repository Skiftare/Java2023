package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        try {
            String[] parts = input.split(" - ");
            String startDateTimeString = parts[0];
            String endDateTimeString = parts[1];

            LocalDateTime startDateTime =
                LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            LocalDateTime endDateTime =
                LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));

            Duration sessionDuration = Duration.between(startDateTime, endDateTime);
            return sessionDuration;
        }
        catch (Exception e){
            throw new RuntimeException("Wrong time format");
        }

    }

    public static String computerClubAnalytics(@NotNull List<String> input) {
        Duration totalDuration = Duration.ZERO;
        if(input.size() > 0) {
            for (String interval : input) {
                totalDuration = totalDuration.plus(calculateDurationForString(interval));
            }

            totalDuration = totalDuration.dividedBy(input.size());
        }
        Long hours = totalDuration.toHours();
        long minutes = totalDuration.toMinutes() % MAX_MIN;
        return hours + "ч " + minutes + "м";
    }
}
