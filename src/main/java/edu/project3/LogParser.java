package edu.project3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.DateFormatter.convertTimeInLogToDate;
import static edu.project3.utility.UtilityClass.BODY_BYTES_SENT_INDEX;
import static edu.project3.utility.UtilityClass.HTTP_REFERER_INDEX;
import static edu.project3.utility.UtilityClass.HTTP_USER_AGENT_INDEX;
import static edu.project3.utility.UtilityClass.LOG_PARTS_COUNT;
import static edu.project3.utility.UtilityClass.REMOTE_ADDR_INDEX;
import static edu.project3.utility.UtilityClass.REMOTE_USER_INDEX;
import static edu.project3.utility.UtilityClass.REQUEST_INDEX;
import static edu.project3.utility.UtilityClass.STATUS_INDEX;
import static edu.project3.utility.UtilityClass.TIME_LOCAL_INDEX;

@SuppressWarnings("HideUtilityClassConstructor")
public class LogParser {

    static String[] stupidParse(String incomeString) {
        String separators = "/: ";
        char plusChar = '+';
        String[] parts = new String[LOG_PARTS_COUNT];
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



    public static LogEntry parse(String logLine) {

        LogEntry entry = new LogEntry();

        String regex = "^(\\S+) - (\\S+) \\[([^]]+)] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {

            entry.setRemoteAddr(matcher.group(REMOTE_ADDR_INDEX));
            entry.setRemoteUser(matcher.group(REMOTE_USER_INDEX));
            entry.setTimeLocal(convertTimeInLogToDate(matcher.group(TIME_LOCAL_INDEX)));
            entry.setRequest(matcher.group(REQUEST_INDEX));
            entry.setStatus(Integer.parseInt(matcher.group(STATUS_INDEX)));
            entry.setBodyBytesSent(Integer.parseInt(matcher.group(BODY_BYTES_SENT_INDEX)));
            entry.setHttpReferer(matcher.group(HTTP_REFERER_INDEX));
            entry.setHttpUserAgent(matcher.group(HTTP_USER_AGENT_INDEX));

            return entry;
        } else {
            throw new IllegalArgumentException("Некорректная строка лога: " + logLine);
        }
    }
}
