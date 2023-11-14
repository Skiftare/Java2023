package edu.project3;

import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

    private static Month convertMonth(String monthString) {
        switch (monthString) {
            case "Jan":
                return Month.JANUARY;
            case "Feb":
                return Month.FEBRUARY;
            case "Mar":
                return Month.MARCH;
            case "Apr":
                return Month.APRIL;
            case "May":
                return Month.MAY;
            case "Jun":
                return Month.JUNE;
            case "Jul":
                return Month.JULY;
            case "Aug":
                return Month.AUGUST;
            case "Sep":
                return Month.SEPTEMBER;
            case "Oct":
                return Month.OCTOBER;
            case "Nov":
                return Month.NOVEMBER;
            case "Dec":
                return Month.DECEMBER;
            default:
                throw new IllegalArgumentException("Некорректный месяц: " + monthString);
        }
    }
    private static String[] stupidParse(String incomeString){

        String[] parts = new String[15];
        StringBuilder buf = new StringBuilder();
        int p = 0;
        for(int i = 0;i<incomeString.length();i++){

            if("/ :   ".contains(incomeString.charAt(i)+" ")){
                //System.out.println(buf);
                parts[p] = buf.toString();
                p++;
                buf = new StringBuilder();
            }
            else{
                if(incomeString.charAt(i)!= '+')
                    buf.append(incomeString.charAt(i));
            }
        }
        parts[p] = buf.toString();
        return parts;
    }

    private static Date convert(String dateString) {
        //String[] parts = dateString.split("[:\\s+]");
        String[] parts = stupidParse(dateString);
        int day = Integer.parseInt(parts[0]);
        Month month = convertMonth(parts[1]);
        int year = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3]);
        int minute = Integer.parseInt(parts[4]);
        int second = Integer.parseInt(parts[5]);
        int offsetHours = Integer.parseInt(parts[6]);
        int offsetMinutes = 0;

        ZoneOffset offset = ZoneOffset.ofHoursMinutes(offsetHours, offsetMinutes);

        return Date.from(OffsetDateTime.of(year, month.getValue(), day, hour, minute, second, 0, offset).toInstant());
    }
    public static LogEntry parse(String logLine) {

        LogEntry entry = new LogEntry();

        String regex = "^([^\\s]+) - - \\[([^\\]]+)\\] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {

            entry.setRemoteAddr(matcher.group(1));
            entry.setTimeLocal(convert(matcher.group(2)));
            entry.setRequest(matcher.group(3));
            entry.setStatus(Integer.parseInt(matcher.group(4)));
            entry.setBodyBytesSent(Integer.parseInt(matcher.group(5)));
            entry.setHttpReferer(matcher.group(6));
            entry.setHttpUserAgent(matcher.group(7));
            return entry;
        } else {

            throw new IllegalArgumentException("Некорректная строка лога: "+logLine);
        }
    }
}
