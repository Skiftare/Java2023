package edu.project3;

import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.DateFormatter.convertMonthForOneLogParsing;

@SuppressWarnings("HideUtilityClassConstructor")
public class LogParser {



    @SuppressWarnings("MagicNumber")
    private static String[] stupidParse(String incomeString) {
        int logPartsCount = 15;
        String separators = "/: ";
        char plusChar = '+';
        String[] parts = new String[logPartsCount];
        StringBuilder buf = new StringBuilder();
        int p = 0;
        for (int i = 0; i < incomeString.length(); i++) {
            if (separators.contains(String.valueOf(incomeString.charAt(i)))) {
                parts[p] = buf.toString();
                p++;
                buf = new StringBuilder();
            } else {
                if (incomeString.charAt(i) != plusChar) {
                    buf.append(incomeString.charAt(i));
                }
            }
        }
        parts[p] = buf.toString();
        return parts;
    }

    private static Date convert(String dateString) {
        String[] parts = stupidParse(dateString);
        int partOfStringWithDay = 0;
        int day = Integer.parseInt(parts[partOfStringWithDay]);
        int monthIndex = 1;
        Month month = convertMonthForOneLogParsing(parts[monthIndex]);
        int yearIndex = 2;
        int year = Integer.parseInt(parts[yearIndex]);
        int hourIndex = 3;
        int hour = Integer.parseInt(parts[hourIndex]);
        int minuteIndex = 4;
        int minute = Integer.parseInt(parts[minuteIndex]);
        int secondIndex = 5;
        int second = Integer.parseInt(parts[secondIndex]);
        int offsetHoursIndex = 6;
        int offsetHours = Integer.parseInt(parts[offsetHoursIndex]);
        int offsetMinutes = 0;
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(offsetHours, offsetMinutes);

        return Date.from(OffsetDateTime.of(year, month.getValue(), day, hour, minute, second, 0, offset).toInstant());
    }

    @SuppressWarnings("MagicNumber")
    public static LogEntry parse(String logLine) {

        LogEntry entry = new LogEntry();

        String regex = "^(\\S+) - (\\S+) \\[([^]]+)] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {

            entry.setRemoteAddr(matcher.group(1));
            entry.setRemoteUser(matcher.group(2));
            entry.setTimeLocal(convert(matcher.group(3)));
            entry.setRequest(matcher.group(4));
            entry.setStatus(Integer.parseInt(matcher.group(5)));
            entry.setBodyBytesSent(Integer.parseInt(matcher.group(6)));
            entry.setHttpReferer(matcher.group(7));
            entry.setHttpUserAgent(matcher.group(8));
            return entry;
        } else {
            throw new IllegalArgumentException("Некорректная строка лога: " + logLine);
        }
    }
}
