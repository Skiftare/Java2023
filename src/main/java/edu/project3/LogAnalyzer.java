package edu.project3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.util.Collections.reverseOrder;

public class LogAnalyzer {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<Date, Integer> TIME_LOCAL_MAP = new HashMap<>();
    private static final Map<String, Integer> REMOTE_ADDR_MAP = new HashMap<>();
    private static final Map<String, Integer> REQUEST_MAP = new HashMap<>();
    private static final Map<Integer, Integer> STATUS_MAP = new HashMap<>();
    private static final Map<Integer, Integer> BODY_BYTES_SENT_MAP = new HashMap<>();
    private static final Map<String, Integer> HTTP_REFERER_MAP = new HashMap<>();
    private static final Map<String, Integer> HTTP_USER_AGENT_MAP = new HashMap<>();
    private static String fileFormat;
    private static String pathToOutputFile;
    private static Date minDate = null;
    private static Date maxDate = null;
    private static Integer totalRequests = 0;
    private static Integer totalResponseSizeByKB = 0;
    private final static String YEAR_MONTH_AND_DATE = "yyyy-MM-dd";
    private final static String HOURS_MINUTES_AND_SECONDS = "HH:mm:ss";
    private final static String HTTP_STRING = "http";
    private final static int BYTES_IN_KILOBYTE = 1024;
    private final static int VARIATIONS_OF_RESPONSE_CODES = 6;
    private final static int MULTIPLIER_FOR_CODES = 100;
    private final static int TOP_N = 10;
    private final static int HOURS_IN_DAY = 24;


    public Map<Date, Integer> getTimeLocalMap() {
        return TIME_LOCAL_MAP;
    }

    public Map<String, Integer> getRemoteAddrMap() {
        return REMOTE_ADDR_MAP;
    }

    public Map<String, Integer> getRequestMap() {
        return REQUEST_MAP;
    }

    public Map<Integer, Integer> getStatusMap() {
        return STATUS_MAP;
    }

    public Map<Integer, Integer> getBodyBytesSentMap() {
        return BODY_BYTES_SENT_MAP;
    }

    public Map<String, Integer> getHttpRefererMap() {
        return HTTP_REFERER_MAP;
    }

    public Map<String, Integer> getHttpUserAgentMap() {
        return HTTP_USER_AGENT_MAP;
    }

    private static <T> void put(Map<T, Integer> m, T val) {
        m.put(val, m.getOrDefault(val, 0) + 1);
    }

    private static Date convert(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(YEAR_MONTH_AND_DATE);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    private static boolean isCurrInRange(Date curr, Date from, Date to) {
        if (from == null && to == null) {
            return true;
        } else if (from == null) {
            return curr.compareTo(to) <= 0;
        } else if (to == null) {
            return (curr.compareTo(from) >= 0);
        } else {
            return (curr.compareTo(from) >= 0 && curr.compareTo(to) <= 0);
        }
    }

    private static void initFile() {

        String folderForOutput = "src/main/java/edu/project3/resources/";
        pathToOutputFile = folderForOutput + "output" + fileFormat;
        try {
            File file = new File(pathToOutputFile);
            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("");
                }
            } else {
                if (!file.createNewFile()) {
                    LOGGER.info("Файл создать не удалось");
                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private static void writeToFile(Table tables) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToOutputFile, true))) {
            writer.write(tables.printAsString(fileFormat));
        } catch (IOException e) {
            LOGGER.info("Ошибка при записи файла: " + e.getMessage());
        }
    }

    private static void putParsedDataToMaps(LogEntry log) {
        put(REMOTE_ADDR_MAP, log.getRemoteAddr());
        put(REQUEST_MAP, log.getRequest());
        put(STATUS_MAP, log.getStatus());
        put(BODY_BYTES_SENT_MAP, log.getBodyBytesSent());
        put(HTTP_REFERER_MAP, log.getHttpReferer());
        put(HTTP_USER_AGENT_MAP, log.getRemoteUser());
        put(TIME_LOCAL_MAP, log.getTimeLocal());
    }

    private static void updateVariablesByLog(LogEntry log) {
        totalRequests++;
        totalResponseSizeByKB += log.getBodyBytesSent() / BYTES_IN_KILOBYTE;

        if (maxDate == null || log.getTimeLocal().compareTo(maxDate) >= 0) {
            maxDate = log.getTimeLocal();
        }
        if (minDate == null || log.getTimeLocal().compareTo(minDate) <= 0) {
            minDate = log.getTimeLocal();
        }
    }

    private static void parseOneLog(String line, Date from, Date to) {
        LogEntry log = LogParser.parse(line);
        if (isCurrInRange(log.getTimeLocal(), from, to)) {
            putParsedDataToMaps(log);
            updateVariablesByLog(log);
        }
    }

    private static List<Path> findLogFiles(String logPath) throws IOException {
        List<Path> logFiles = new ArrayList<>();

        File file = new File(logPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (File f : files) {
                    logFiles.add(Paths.get(f.getAbsolutePath()));
                }
            }
        } else if (file.isFile()) {
            logFiles.add(Paths.get(file.getAbsolutePath()));
        } else {
            logFiles = findFilesRecursively(logPath);
        }
        return logFiles;
    }

    private static List<Path> findFilesRecursively(String logPath) {
        List<Path> logFiles = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] folders = logPath.split("/");
        for (String subDir : folders) {
            if (Pattern.matches("[\\w.-]+", subDir)) {
                sb.append(subDir).append('/');
            } else {
                break;
            }
        }
        String baseDir = sb.toString();
        try (Stream<Path> stream = Files.walk(Paths.get(baseDir))) {
            List<File> subPaths = stream
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .toList();
            for (File subPath : subPaths) {
                if (subPath.toPath().getFileName().toString().equals(folders[folders.length - 1])) {
                    logFiles.add(subPath.toPath());
                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

        return logFiles;
    }

    private static String parseArrayAsOneString(ArrayList<String> incomeArray) {
        StringBuilder sb = new StringBuilder();
        for (String element : incomeArray) {
            sb.append(element).append('\t');
        }
        return sb.toString();
    }

    public static void resetStaticVariables() {
        TIME_LOCAL_MAP.clear();
        REMOTE_ADDR_MAP.clear();
        REQUEST_MAP.clear();
        STATUS_MAP.clear();
        BODY_BYTES_SENT_MAP.clear();
        HTTP_REFERER_MAP.clear();
        HTTP_USER_AGENT_MAP.clear();
        minDate = null;
        maxDate = null;
        totalRequests = 0;
        totalResponseSizeByKB = 0;
    }

    //main static; accept path to logs with output format && optional parameters, such as filter by date
    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        String path = null;
        Date from = null;
        Date to = null;
        String fromStr = null;
        String toStr = null;
        String line;
        ArrayList<String> readedLogs = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--path":
                    path = args[i + 1];
                    break;
                case "--from":
                    from = convert(args[i + 1]);
                    fromStr = args[i + 1];
                    break;
                case "--to":
                    to = convert(args[i + 1]);
                    toStr = args[i + 1];
                    break;
                case "--format":
                    fileFormat = args[i + 1];
                    break;
                default:
            }
        }
        if (path == null) {
            throw new RuntimeException("no path to parse logs");
        }
        assert fileFormat != null;
        fileFormat = fileFormat.equals("markdown") ? ".md" : ".adoc";
        initFile();
        try {
            if (path.startsWith(HTTP_STRING)) {
                URL url = URI.create(path).toURL();
                readedLogs.add(String.valueOf(url));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((line = reader.readLine()) != null) {
                    parseOneLog(line, from, to);
                }

                reader.close();
            } else {
                List<Path> paths = findLogFiles(path);
                for (Path logPath : paths) {
                    readedLogs.add(logPath.toString());
                    BufferedReader reader = Files.newBufferedReader(logPath);
                    while ((line = reader.readLine()) != null) {
                        parseOneLog(line, from, to);
                    }
                    reader.close();
                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

        LOGGER.info("Логи обработаны, началась запись в:");
        LOGGER.info(pathToOutputFile);
        writeAllStats(readedLogs, fromStr, toStr);
        LOGGER.info("Логи записаны");


    }

    private static void writeAllStats(ArrayList<String> path, String fromStr, String toStr) {
        printGeneralInfo(path, fromStr, toStr);
        printResourceStats();
        printResponseCodeStats();
        printFirstExtraMetric();
        printSecondExtraMetric();
        printUserAgentStats();
        printTopNRequestedResources(TOP_N);
        printBusiestPeriodMetric();
        printRequestDistributionByHour();

    }

    private static void printFirstExtraMetric() {
        SimpleDateFormat dateFormatForOutput = new SimpleDateFormat(
            YEAR_MONTH_AND_DATE + "'  '" + HOURS_MINUTES_AND_SECONDS
        );
        Table table = new Table("Метрика дат из логов", "Дата");
        table.nameTable("Первая доп. метрика");
        table.addRow(
            "С какого момента стали поступать логи:",
            minDate == null ? "-" : dateFormatForOutput.format(minDate)
        );
        table.addRow(
            "До какого момента поступали логи: ",
            maxDate == null ? "-" : dateFormatForOutput.format(maxDate)
        );
        writeToFile(table);
    }

    private static void printSecondExtraMetric() {
        Table table = new Table("Статус ответа", "Количество ответов");
        table.nameTable("Вторая доп. метрика");
        int firstNumber;
        int[] respCodes = new int[VARIATIONS_OF_RESPONSE_CODES];
        for (Map.Entry<Integer, Integer> entry : STATUS_MAP.entrySet()) {
            firstNumber = entry.getKey() / MULTIPLIER_FOR_CODES - (entry.getKey() % MULTIPLIER_FOR_CODES == 0 ? 1 : 0);
            respCodes[firstNumber] += entry.getValue();
        }
        for (int i = 1; i < VARIATIONS_OF_RESPONSE_CODES; i++) {

            table.addRow(
                ResponseCodeParser.analyzeStatusCode(MULTIPLIER_FOR_CODES * i + 1),
                Integer.toString(respCodes[i])
            );
        }
        writeToFile(table);
    }

    private static void printUserAgentStats() {
        Table table = new Table("Пользовательский агент", "Число запросов");
        table.nameTable("Статистика пользовательских агентов");
        for (Map.Entry<String, Integer> entry : HTTP_USER_AGENT_MAP.entrySet()) {
            table.addRow(entry.getKey() == null ? "-" : entry.getKey(), entry.getValue().toString());
        }
        writeToFile(table);
    }

    private static void printTopNRequestedResources(int topN) {
        Table table = new Table("Сайт/источник", "Кол-во обращений");
        table.nameTable("Топ запрашиваемых ресурсов");
        List<Map.Entry<String, Integer>> sortedResources = new ArrayList<>(REQUEST_MAP.entrySet());
        sortedResources.sort(Map.Entry.comparingByValue(reverseOrder()));
        for (int i = 0; i < Math.min(topN, sortedResources.size()); i++) {
            Map.Entry<String, Integer> entry = sortedResources.get(i);
            table.addRow(entry.getKey(), entry.getValue().toString());
        }

        writeToFile(table);
    }

    private static void printRequestDistributionByHour() {
        Table table = new Table("Час", "Кол-во запросов");
        table.nameTable("Распределение запросов по часам");
        int[] requestCountsByHour = new int[HOURS_IN_DAY];
        for (Map.Entry<Date, Integer> entry : TIME_LOCAL_MAP.entrySet()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(entry.getKey());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            requestCountsByHour[hour] += entry.getValue();
        }
        for (int hour = 0; hour < HOURS_IN_DAY; hour++) {
            table.addRow(String.format("%02d:00 - %02d:59", hour, hour), Integer.toString(requestCountsByHour[hour]));
        }

        writeToFile(table);
    }

    private static void printBusiestPeriodMetric() {
        Map<Integer, Integer> yearFrequency = new HashMap<>();
        Map<Integer, Integer> monthFrequency = new HashMap<>();
        Map<Integer, Integer> weekFrequency = new HashMap<>();
        Map<Integer, Integer> dayOfWeekFrequency = new HashMap<>();
        for (Date date : TIME_LOCAL_MAP.keySet()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            yearFrequency.put(year, yearFrequency.getOrDefault(year, 0) + 1);
            monthFrequency.put(month, monthFrequency.getOrDefault(month, 0) + 1);
            weekFrequency.put(week, weekFrequency.getOrDefault(week, 0) + 1);
            dayOfWeekFrequency.put(dayOfWeek, dayOfWeekFrequency.getOrDefault(dayOfWeek, 0) + 1);
        }
        int busiestYear = findBusiestPeriod(yearFrequency);
        int busiestMonth = findBusiestPeriod(monthFrequency);
        int busiestWeek = findBusiestPeriod(weekFrequency);
        int busiestDayOfWeek = findBusiestPeriod(dayOfWeekFrequency);
        Table table = new Table("Загруженность", "Момент максимальной ногрузки");
        table.nameTable("Третья доп. метрика");
        table.addRow(
            "Год", Integer.toString(busiestYear),
            "Месяц", Integer.toString(busiestMonth),
            "№ недели в году", Integer.toString(busiestWeek),
            "День недели", Integer.toString(busiestDayOfWeek)
        );
        writeToFile(table);
    }

    private static int findBusiestPeriod(Map<Integer, Integer> frequencyMap) {
        int busiestPeriod = -1;
        int maxFrequency = 0;

        // Iterate over the frequency map to find the busiest period
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int period = entry.getKey();
            int frequency = entry.getValue();

            if (frequency > maxFrequency) {
                busiestPeriod = period;
                maxFrequency = frequency;
            }
        }

        return busiestPeriod;
    }







    private static void printGeneralInfo(ArrayList<String> path, String fromDate, String toDate) {
        Table table = new Table("Метрика", "Значение");
        table.nameTable("Общая информация");
        if (path.get(0).startsWith(HTTP_STRING)) {
            table.addRow("URL", parseArrayAsOneString(path));
        } else {
            table.addRow("Файл(ы)", parseArrayAsOneString(path));
        }
        table.addRow("Начальная дата", (fromDate != null ? fromDate : "-"));
        table.addRow("Конечная дата", (toDate != null ? toDate : "-"));
        table.addRow("Количество запросов", Integer.toString(totalRequests));
        table.addRow(
            "Средний размер ответа (в КБ)",
            (totalRequests > 0 ? (totalResponseSizeByKB / totalRequests) + "KB" : "-")
        );
        writeToFile(table);

    }

    private static void printResourceStats() {
        Table table = new Table("Ресурс", "Запросов");
        table.nameTable("Запрашиваемые ресурсы");
        for (Map.Entry<String, Integer> entry : HTTP_REFERER_MAP.entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        writeToFile(table);
    }

    private static void printResponseCodeStats() {
        Table table = new Table("Код", "Имя", "Количество");
        table.nameTable("Коды ответа");
        for (Map.Entry<Integer, Integer> entry : STATUS_MAP.entrySet()) {
            table.addRow(
                Integer.toString(entry.getKey()),
                ResponseCodeParser.getResponseCodeName(Integer.toString(entry.getKey())),
                Integer.toString(entry.getValue())
            );
        }
        writeToFile(table);

    }
}
