package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {
    private static final int MAX_MIN = 60;
    private static final String PATTERN_STRING = "yyyy-MM-dd, HH:mm";
    private static final String EXCEPTION_MESSAGE = "Wrong time format";

    private static Duration calculateDurationForString(@NotNull String input) {
        try {
            String[] parts = input.split(" - ");
            String startDateTimeString = parts[0];
            String endDateTimeString = parts[1];

            LocalDateTime startDateTime =
                LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern(PATTERN_STRING));
            LocalDateTime endDateTime =
                LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern(PATTERN_STRING));

            Duration sessionDuration = Duration.between(startDateTime, endDateTime);
            if (sessionDuration.isNegative()) {
                throw new RuntimeException(EXCEPTION_MESSAGE);
            }
            return sessionDuration;
        } catch (Exception e) {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }

    }

    public static String computerClubAnalytics(@NotNull List<String> input) {
        Duration totalDuration = Duration.ZERO;
        if (!input.isEmpty()) {
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
