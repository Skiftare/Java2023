package edu.project3;

import edu.project3.utility.UtilityClass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import static edu.project3.LogParser.stupidParse;

@SuppressWarnings("HideUtilityClassConstructor")
public class DateFormatter {

    private static final int PART_OF_STRING_WITH_DAY = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;
    private static final int HOUR_INDEX = 3;
    private static final int MINUTE_INDEX = 4;
    private static final int SECOND_INDEX = 5;
    private static final int OFFSET_HOURS_INDEX = 6;

    static Date convertForParsingArguments(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(UtilityClass.YEAR_MONTH_AND_DATE);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            ErrorLogger.createLogError(e.getMessage());
            return null;
        }
    }

    static String  getCurrentDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        return dateFormat.format(new Date());
    }

    static Month convertMonthForOneLogParsing(String monthString) {
        return switch (monthString) {
            case "Jan" -> Month.JANUARY;
            case "Feb" -> Month.FEBRUARY;
            case "Mar" -> Month.MARCH;
            case "Apr" -> Month.APRIL;
            case "May" -> Month.MAY;
            case "Jun" -> Month.JUNE;
            case "Jul" -> Month.JULY;
            case "Aug" -> Month.AUGUST;
            case "Sep" -> Month.SEPTEMBER;
            case "Oct" -> Month.OCTOBER;
            case "Nov" -> Month.NOVEMBER;
            case "Dec" -> Month.DECEMBER;
            default -> throw new IllegalArgumentException("Некорректный месяц: " + monthString);
        };
    }

    static String formatDateForLogs(Date date) {
        SimpleDateFormat dateFormatForOutput = new SimpleDateFormat(
            UtilityClass.getYearMonthAndDate() + "'  '" + UtilityClass.getHoursMinutesAndSeconds()
        );
        return dateFormatForOutput.format(date);
    }

    static Date convertTimeInLogToDate(String dateString) {
        String[] parts = stupidParse(dateString);

        int day = Integer.parseInt(parts[PART_OF_STRING_WITH_DAY]);
        Month month = convertMonthForOneLogParsing(parts[MONTH_INDEX]);
        int year = Integer.parseInt(parts[YEAR_INDEX]);
        int hour = Integer.parseInt(parts[HOUR_INDEX]);
        int minute = Integer.parseInt(parts[MINUTE_INDEX]);
        int second = Integer.parseInt(parts[SECOND_INDEX]);
        int offsetHours = Integer.parseInt(parts[OFFSET_HOURS_INDEX]);

        ZoneOffset offset = ZoneOffset.ofHoursMinutes(offsetHours, 0);

        return Date.from(OffsetDateTime.of(year, month.getValue(), day, hour, minute, second, 0, offset).toInstant());
    }
}
