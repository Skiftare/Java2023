package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static Optional<LocalDate> parseDate(String string) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate date = LocalDate.parse(string, formatter);
                return Optional.of(date);
            } catch (Exception e) {
                // Ignore parsing exceptions and try the next formatter
            }
        }

        if (string.equalsIgnoreCase("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else if (string.equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        } else if (string.equalsIgnoreCase("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        } else {
            Pattern pattern = Pattern.compile("(\\d+)\\s+(day|days)\\s+ago");
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                int daysAgo = Integer.parseInt(matcher.group(1));
                return Optional.of(LocalDate.now().minusDays(daysAgo));
            }
        }

        return Optional.empty();
    }
}