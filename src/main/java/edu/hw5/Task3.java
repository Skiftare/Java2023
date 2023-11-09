package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    private final static Logger LOGGER = LogManager.getLogger();

    public static Optional<LocalDate> parseDate(String string) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yy")
        };


        Optional<LocalDate> res = Optional.empty();
        if (string.equalsIgnoreCase("tomorrow")) {
            res = Optional.of(LocalDate.now().plusDays(1));
        } else if (string.equalsIgnoreCase("today")) {
            res = Optional.of(LocalDate.now());
        } else if (string.equalsIgnoreCase("yesterday")) {
            res = Optional.of(LocalDate.now().minusDays(1));
        } else {
            Pattern pattern = Pattern.compile("(\\d+)\\s+(day|days)\\s+ago");
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                int daysAgo = Integer.parseInt(matcher.group(1));
                res = Optional.of(LocalDate.now().minusDays(daysAgo));
            }
        }
        if (res.isEmpty()) {
            for (DateTimeFormatter formatter : formatters) {
                try {
                    LocalDate date = LocalDate.parse(string, formatter);
                    res = Optional.of(date);

                } catch (Exception e) {
                    LOGGER.info("Failed to parse {} using format {}: {}", string, formatter, e.getMessage());
                }
            }
        }

        return res;
    }
}
