package edu.project3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.DateFormatter.convertTimeInLogToDate;

@SuppressWarnings("HideUtilityClassConstructor")
public class LogParser {



    @SuppressWarnings("MagicNumber")
    static String[] stupidParse(String incomeString) {
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



    @SuppressWarnings("MagicNumber")
    public static LogEntry parse(String logLine) {

        LogEntry entry = new LogEntry();

        String regex = "^(\\S+) - (\\S+) \\[([^]]+)] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {

            entry.setRemoteAddr(matcher.group(1));
            entry.setRemoteUser(matcher.group(2));
            entry.setTimeLocal(convertTimeInLogToDate(matcher.group(3)));
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
