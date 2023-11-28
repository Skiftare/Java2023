package edu.project3.utility;

@SuppressWarnings("HideUtilityClassConstructor")
public class UtilityClass {
    public final static String YEAR_MONTH_AND_DATE = "yyyy-MM-dd";
    public final static String HOURS_MINUTES_AND_SECONDS = "HH:mm:ss";
    public final static String HTTP_STRING = "http";
    public final static int BYTES_IN_KILOBYTE = 1024;
    public final static int VARIATIONS_OF_RESPONSE_CODES = 6;
    public final static int MULTIPLIER_FOR_CODES = 100;
    public final static int INFORMATIONAL_MIN = 100;
    public final static int INFORMATIONAL_MAX = 200;
    public final static int SUCCESSFUL_MIN = 200;
    public final static int SUCCESSFUL_MAX = 300;
    public final static int REDIRECTION_MIN = 300;
    public final static int REDIRECTION_MAX = 400;
    public final static int CLIENT_ERROR_MIN = 400;
    public final static int CLIENT_ERROR_MAX = 500;
    public final static int SERVER_ERROR_MIN = 500;
    public final static int SERVER_ERROR_MAX = 600;
    public final static int NEEDED_CELL_SIZE = 3;
    public static final int PART_OF_STRING_WITH_DAY = 0;
    public static final int MONTH_INDEX = 1;
    public static final int YEAR_INDEX = 2;
    public static final int HOUR_INDEX = 3;
    public static final int MINUTE_INDEX = 4;
    public static final int SECOND_INDEX = 5;
    public static final int OFFSET_HOURS_INDEX = 6;
    public static final int REMOTE_ADDR_INDEX = 1;
    public static final int REMOTE_USER_INDEX = 2;
    public static final int TIME_LOCAL_INDEX = 3;
    public static final int REQUEST_INDEX = 4;
    public static final int STATUS_INDEX = 5;
    public static final int BODY_BYTES_SENT_INDEX = 6;
    public static final int HTTP_REFERER_INDEX = 7;
    public static final int HTTP_USER_AGENT_INDEX = 8;
    public static final int LOG_PARTS_COUNT = 15;
    public static final int COUNT_OF_USER_AGENT_ROWS = 10;
    public static final String HTTP_URL = "https://developer.mozilla.org/ru/docs/Web/HTTP/Status";
    //Тут геттеры не для всего, потому что это же как бы константы, их можно и так подключать.

    public static String getYearMonthAndDate() {
        return YEAR_MONTH_AND_DATE;
    }

    public static String getHoursMinutesAndSeconds() {
        return HOURS_MINUTES_AND_SECONDS;
    }

    public static String getHttpString() {
        return HTTP_STRING;
    }

    public static int getBytesInKilobyte() {
        return BYTES_IN_KILOBYTE;
    }

    public static int getVariationsOfResponseCodes() {
        return VARIATIONS_OF_RESPONSE_CODES;
    }

    public static int getMultiplierForCodes() {
        return MULTIPLIER_FOR_CODES;
    }
}
