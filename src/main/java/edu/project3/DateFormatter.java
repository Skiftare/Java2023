package edu.project3;

import edu.project3.utility.UtilityClass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;

public class DateFormatter {

    static Date convertForParsingArguments(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(UtilityClass.YEAR_MONTH_AND_DATE);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            ErrorLogger.createLogError(e.getMessage());
            return null;
        }
    }
    static String  getCurrentDateAsString(){
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
    static String formatDateForLogs(Date date){
        SimpleDateFormat dateFormatForOutput = new SimpleDateFormat(
            UtilityClass.getYearMonthAndDate() + "'  '" + UtilityClass.getHoursMinutesAndSeconds()
        );
        return dateFormatForOutput.format(date);
    }
}
