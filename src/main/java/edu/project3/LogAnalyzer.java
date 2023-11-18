package edu.project3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogAnalyzer {

    private static final Logger logger = LogManager.getLogger();
    private final Map<Date, Integer> timeLocalMap = new HashMap<>();
    private final Map<String, Integer> remoteAddrMap = new HashMap<>();
    private final Map<String, Integer> requestMap = new HashMap<>();
    private final Map<Integer, Integer> statusMap = new HashMap<>();
    private final Map<Integer, Integer> bodyBytesSentMap = new HashMap<>();
    private final Map<String, Integer> httpRefererMap = new HashMap<>();
    private final Map<String, Integer> httpUserAgentMap = new HashMap<>();
    private String fileFormat;
    private String pathToOutputFile;
    private Date minDate = null;
    private Date maxDate = null;
    private Integer totalRequests = 0;
    private Integer totalResponseSizeByKB = 0;
    private final String yearMonthAndDate = "yyyy-MM-dd";
    private final String hoursMinutestAndSeconds = "HH:mm:ss";
    private final String httpString = "http";


    public Map<Date, Integer> getTimeLocalMap() {
        return timeLocalMap;
    }

    public Map<String, Integer> getRemoteAddrMap() {
        return remoteAddrMap;
    }

    public Map<String, Integer> getRequestMap() {
        return requestMap;
    }

    public Map<Integer, Integer> getStatusMap() {
        return statusMap;
    }

    public Map<Integer, Integer> getBodyBytesSentMap() {
        return bodyBytesSentMap;
    }

    public Map<String, Integer> getHttpRefererMap() {
        return httpRefererMap;
    }

    public Map<String, Integer> getHttpUserAgentMap() {
        return httpUserAgentMap;
    }



    private <T> void put(Map<T, Integer> m, T val) {
        m.put(val, m.getOrDefault(val, 0) + 1);
    }

    private static Date convert(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    private boolean isCurrInRange(Date curr, Date from, Date to) {
        if (from == null && to == null) {
            return true;
        } else if (from == null && to != null) {
            return curr.compareTo(to) <= 0;
        } else if (from != null && to == null) {
            return (curr.compareTo(from) >= 0);
        } else {
            return (curr.compareTo(from) >= 0 && curr.compareTo(to) <= 0);
        }
    }

    private void initFile() {

        StringBuilder filePathBuilder = new StringBuilder();
        String folderForOutput = "src/main/java/edu/project3/resources/";
        filePathBuilder.append(folderForOutput).append("output").append(fileFormat);
        pathToOutputFile = filePathBuilder.toString();
        try {
            File file = new File(pathToOutputFile);
            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("");
                }
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    private void writeToFile(Table tables) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToOutputFile, true))) {
            writer.write(tables.printAsString(fileFormat));
        } catch (IOException e) {
            logger.info("Ошибка при записи файла: " + e.getMessage());
        }
    }

    private void putParsedDataToMaps(LogEntry log) {
        put(remoteAddrMap, log.getRemoteAddr());
        put(requestMap, log.getRequest());
        put(statusMap, log.getStatus());
        put(bodyBytesSentMap, log.getBodyBytesSent());
        put(httpRefererMap, log.getHttpReferer());
        put(httpUserAgentMap, log.getRemoteUser());
        put(timeLocalMap, log.getTimeLocal());
    }

    @SuppressWarnings("MagicNumber")
    private void updateVariablesByLog(LogEntry log) {
        int bytesInKilobyte = 1024;
        totalRequests++;
        totalResponseSizeByKB += log.getBodyBytesSent() / bytesInKilobyte;


        if (maxDate == null || log.getTimeLocal().compareTo(maxDate) >= 0) {
            maxDate = log.getTimeLocal();
        }
        if (minDate == null || log.getTimeLocal().compareTo(minDate) <= 0) {
            minDate = log.getTimeLocal();
        }
    }

    private void parseOneLog(String line, Date from, Date to) {
        LogEntry log = LogParser.parse(line);
        if (isCurrInRange(log.getTimeLocal(), from, to)) {
            putParsedDataToMaps(log);
            updateVariablesByLog(log);
        }
    }

    private List<Path> findLogFiles(String logPath) throws IOException {
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

    private List<Path> findFilesRecursively(String logPath) throws IOException {
        List<Path> logFiles = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] folders = logPath.split("/");
        for (String subDir: folders) {
            if (Pattern.matches("[\\w.-]+", subDir)) {
                sb.append(subDir).append('/');
            } else {
                break;
            }
        }
        String  baseDir = sb.toString();
        List<File> subPaths =
            Files.walk(Paths.get(baseDir))
            .filter(Files::isRegularFile)
            .map(Path::toFile)
            .toList();
        for (File subPath : subPaths) {
            if (subPath.toPath().getFileName().toString().equals(folders[folders.length - 1])) {
                logFiles.add(subPath.toPath());
            }
        }

        return logFiles;
    }

    private String parseArrayAsOneString(ArrayList<String> incomeArray) {
        StringBuilder sb = new StringBuilder();
        for (String element: incomeArray) {
            sb.append(element).append('\t');
        }
        return sb.toString();
    }


    public void main(String[] args) {
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
            if (path.startsWith(httpString)) {
                URL url = new URL(path);
                readedLogs.add(String.valueOf(url));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((line = reader.readLine()) != null) {
                    parseOneLog(line, from, to);
                }

                reader.close();
            } else {
                List<Path> paths = findLogFiles(path);
                for (Path logPath: paths) {
                    readedLogs.add(logPath.toString());
                    BufferedReader reader = Files.newBufferedReader(logPath);
                    while ((line = reader.readLine()) != null) {
                        parseOneLog(line, from, to);
                    }
                    reader.close();
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }

        logger.info("Логи обработаны, началась запись в:");
        logger.info(pathToOutputFile);
        writeAllStats(readedLogs, fromStr, toStr);
        logger.info("Логи записаны");

    }

    private void writeAllStats(ArrayList<String> path, String fromStr, String toStr) {
        printGeneralInfo(path, fromStr, toStr);
        printResourceStats();
        printResponseCodeStats();
        printFirstExtraMetric();
        printSecondExtraMetric();
    }

    private void printFirstExtraMetric() {
        SimpleDateFormat dateFormatForOutput = new SimpleDateFormat(
            yearMonthAndDate + "'  '" + hoursMinutestAndSeconds
        );
        Table table = new Table("Метрика дат из логов", "Дата");
        table.nameTable("Первая доп. метрика");
        table.addRow("С какого момента стали поступать логи:",
            minDate == null ? "-" : dateFormatForOutput.format(minDate)
        );
        table.addRow("До какого момента поступали логи: ",
            maxDate == null ? "-" : dateFormatForOutput.format(maxDate)
        );
        writeToFile(table);
    }

    @SuppressWarnings("MagicNumber")
    private void printSecondExtraMetric() {
        Table table = new Table("Статус ответа", "Количество ответов");
        table.nameTable("Вторая доп. метрика");
        int variationsOfResponceCodes = 6;
        int multiplyerForCodes = 100;
        int firstNumber = 0;
        int[] respCodes = new int[variationsOfResponceCodes];
        for (Map.Entry<Integer, Integer> entry : statusMap.entrySet()) {
            firstNumber = entry.getKey() / multiplyerForCodes - (entry.getKey() % multiplyerForCodes == 0 ? 1 : 0);
            respCodes[firstNumber] += entry.getValue();
        }
        for (int i = 1; i < variationsOfResponceCodes; i++) {

            table.addRow(
                ResponseCodeParser.analyzeStatusCode(multiplyerForCodes * i + 1),
                Integer.toString(respCodes[i])
            );
        }
        writeToFile(table);
    }

    private void printGeneralInfo(
        ArrayList<String> path,
        String fromDate,
        String toDate
    ) {
        Table table = new Table("Метрика", "Значение");
        table.nameTable("Общая информация");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (path.get(0).startsWith(httpString)) {
            table.addRow("URL", parseArrayAsOneString(path));
        } else {
            table.addRow("Файл(ы)", parseArrayAsOneString(path));
        }
        table.addRow("Начальная дата", (fromDate != null ? fromDate : "-"));
        table.addRow("Конечная дата", (toDate != null ? toDate : "-"));
        table.addRow("Количество запросов", Integer.toString(totalRequests));
        table.addRow("Средний размер ответа (в КБ)",
            (totalRequests > 0 ? (totalResponseSizeByKB / totalRequests) + "KB" : "-"));
        writeToFile(table);


    }

    private void printResourceStats() {

        Table table = new Table("Ресурс", "Запросов");
        table.nameTable("Запрашиваемые ресурсы");
        for (Map.Entry<String, Integer> entry : httpRefererMap.entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        writeToFile(table);

    }

    private void printResponseCodeStats() {

        Table table = new Table("Код", "Имя", "Количество");
        table.nameTable("Коды ответа");
        for (Map.Entry<Integer, Integer> entry : statusMap.entrySet()) {
            table.addRow(
                Integer.toString(entry.getKey()),
                ResponseCodeParser.getResponseCodeName(Integer.toString(entry.getKey())),
                Integer.toString(entry.getValue())
            );
        }
        writeToFile(table);

    }
}
