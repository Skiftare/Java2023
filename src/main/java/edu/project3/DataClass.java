package edu.project3;

import edu.project3.utility.UtilityClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
public class DataClass {
    private static final Map<Date, Integer> TIME_LOCAL_MAP = new HashMap<>();
    private static final Map<String, Integer> REMOTE_ADDR_MAP = new HashMap<>();
    private static final Map<String, Integer> REQUEST_MAP = new HashMap<>();
    private static final Map<Integer, Integer> STATUS_MAP = new HashMap<>();
    private static final Map<Integer, Integer> BODY_BYTES_SENT_MAP = new HashMap<>();
    private static final Map<String, Integer> HTTP_REFERER_MAP = new HashMap<>();
    private static Map<String, Integer> HTTP_USER_AGENT_MAP = new HashMap<>();
    private static final Map<String, Integer> REMOTE_USER = new HashMap<>();
    private static final ArrayList<String> READED_LOGS = new ArrayList<>();
    private static Date minDate = null;
    private static Date maxDate = null;
    private static Integer totalRequests = 0;
    private static Integer totalResponseSizeByKB = 0;

    public static Date getMinDate() {
        return  minDate;
    }

    public static Date getMaxDate() {
        return  maxDate;
    }

    public static Integer getTotalRequests() {
        return totalRequests;
    }

    public static Integer getTotalResponseSizeByKB() {
        return totalResponseSizeByKB;
    }

    public static Map<Date, Integer> getTimeLocalMap() {
        return TIME_LOCAL_MAP;
    }

    public static Map<String, Integer> getRemoteAddrMap() {
        return REMOTE_ADDR_MAP;
    }

    public static Map<String, Integer> getRequestMap() {
        return REQUEST_MAP;
    }

    public static Map<Integer, Integer> getStatusMap() {
        return STATUS_MAP;
    }

    public static Map<Integer, Integer> getBodyBytesSentMap() {
        return BODY_BYTES_SENT_MAP;
    }

    public static Map<String, Integer> getHttpRefererMap() {
        return HTTP_REFERER_MAP;
    }

    public static Map<String, Integer> getHttpUserAgentMap() {
        return HTTP_USER_AGENT_MAP;
    }

    public static Map<String, Integer> getRemoteUserMap() {
        return REMOTE_USER;
    }

    public static ArrayList<String> getReadedLogs() {
        return READED_LOGS;
    }


    public static void addReadedLog(String s) {
        READED_LOGS.add(s);
    }

    private static <T> void put(Map<T, Integer> m, T val) {
        m.put(val, m.getOrDefault(val, 0) + 1);
    }

    public static void putParsedLogToDataClass(LogEntry log) {
        put(REMOTE_ADDR_MAP, log.getRemoteAddr());
        put(REQUEST_MAP, log.getRequest());
        put(STATUS_MAP, log.getStatus());
        put(BODY_BYTES_SENT_MAP, log.getBodyBytesSent());
        put(HTTP_REFERER_MAP, log.getHttpReferer());
        put(HTTP_USER_AGENT_MAP, log.getHttpUserAgent());
        put(REMOTE_USER, log.getRemoteUser());
        put(TIME_LOCAL_MAP, log.getTimeLocal());

        totalRequests++;
        totalResponseSizeByKB += log.getBodyBytesSent() / UtilityClass.getBytesInKilobyte();

        if (maxDate == null || log.getTimeLocal().compareTo(maxDate) >= 0) {
            maxDate = log.getTimeLocal();
        }
        if (minDate == null || log.getTimeLocal().compareTo(minDate) <= 0) {
            minDate = log.getTimeLocal();
        }
    }

    public static void resetStaticVariables() {
        TIME_LOCAL_MAP.clear();
        REMOTE_ADDR_MAP.clear();
        REQUEST_MAP.clear();
        STATUS_MAP.clear();
        BODY_BYTES_SENT_MAP.clear();
        HTTP_REFERER_MAP.clear();
        HTTP_USER_AGENT_MAP.clear();
        REMOTE_USER.clear();
        READED_LOGS.clear();
        minDate = null;
        maxDate = null;
        totalRequests = 0;
        totalResponseSizeByKB = 0;
    }
}
