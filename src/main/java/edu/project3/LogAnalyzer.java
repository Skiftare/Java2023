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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sk.PrettyTable;

public class LogAnalyzer {

    private final static Logger LOGGER = LogManager.getLogger();

    private static final Map<Date, Integer> timeLocalMap = new HashMap<>();
    private static final Map<String, Integer> adressMap = new HashMap<>();
    private static final Map<String, Integer> requestMap = new HashMap<>();
    private static final Map<Integer, Integer> statusMap = new HashMap<>();
    private static final Map<Integer, Integer> bodyBytesSentMap = new HashMap<>();
    private static final Map<String, Integer> refererMap = new HashMap<>();
    private static final Map<String, Integer> agentMap = new HashMap<>();
    private static String FILE_FORMAT;
    private static String PATH_TO_OUTPUT_FILE;
    private static final String FOLDER_FOR_OUTPUT = "src/main/java/edu/project3/resources/";

    private static <T> void put(Map<T, Integer> m, T val) {
        m.put(val, m.getOrDefault(val, 0) + 1);
    }

    private static Date convert(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isCurrInRange(Date curr, Date from, Date to) {
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
    private static void initFile() {

        StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(FOLDER_FOR_OUTPUT).append("output").append(FILE_FORMAT);
        PATH_TO_OUTPUT_FILE = filePathBuilder.toString();
        try {
            File file = new File(PATH_TO_OUTPUT_FILE);
            if (file.exists()) {
                try(FileWriter writer = new FileWriter(file)) {
                    writer.write("");
                }
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private static void writeToFile(PrettyTable tables) {
         // Имя файла с расширением в зависимости от формата

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_OUTPUT_FILE, true))) {
            writer.write(String.valueOf(tables));
        } catch (IOException e) {
            LOGGER.info("Ошибка при записи файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String path = null;
        Date from = null;
        String format = null;
        Date to = null;
        String fromStr = null;
        String toStr = null;
        Date minDate = null;
        Date maxDate = null;

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
                    format = args[i + 1];
                    break;
            }
        }
        if (path == null) {
            throw new RuntimeException("no path to parse logs");
        }
        if(format == null){
            FILE_FORMAT = ".txt";
        }
        else{
            FILE_FORMAT = format.equals("markdown") ? ".md" : ".adoc";
        }
        initFile();

        int totalRequests = 0;
        int totalResponseSizeByKB = 0;
        BufferedReader reader = null;
        try {
            if (path.startsWith("http")) {
                URL url = new URL(path);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
            } else {
                Path logPath = Paths.get(path);
                reader = Files.newBufferedReader(logPath);
            }

            String line;
            while ((line = reader.readLine()) != null) {

                LogEntry log = LogParser.parse(line);
                Date curr = log.getTimeLocal();
                if (isCurrInRange(curr, from, to)) {
                    put(adressMap, log.getRemoteAddr());
                    totalRequests++;
                    put(requestMap, log.getRequest());
                    put(statusMap, log.getStatus());
                    totalResponseSizeByKB += (log.getBodyBytesSent() / (8 * 1024));
                    put(bodyBytesSentMap, log.getBodyBytesSent());
                    put(refererMap, log.getHttpReferer());
                    put(agentMap, log.getRemoteUser());

                    if (maxDate == null || curr.compareTo(maxDate) >= 0) {
                        maxDate = curr;
                    }
                    if (minDate == null || curr.compareTo(minDate) <= 0) {
                        minDate = curr;
                    }
                    put(timeLocalMap, curr);
                }

            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info("Логи обрботаны, началась запись");
        printGeneralInfo(path, fromStr, toStr, totalRequests, totalResponseSizeByKB);
        printResourceStats();
        printResponseCodeStats();

        printFirstExtraMetric(minDate, maxDate);
        printSecondExtraMetric();
        LOGGER.info("Логи записаны");

    }

    private static void printFirstExtraMetric(Date minDate, Date maxDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'  'HH:mm:ss");
        PrettyTable table = new PrettyTable("Метрика", "значение");
        table.addRow("С какого момента стали поступать логи:", dateFormat.format(minDate));
        table.addRow("До какого момента поступали логи: ", dateFormat.format(maxDate));
        LOGGER.info(table);
        writeToFile(table);
    }

    private static void printSecondExtraMetric() {
        PrettyTable table = new PrettyTable("Статус ответа", "Количество ответов");
        int[] respCodes = new int[6];
        for (Map.Entry<Integer, Integer> entry : LogAnalyzer.statusMap.entrySet()) {
            respCodes[entry.getKey() / 100]+=entry.getValue();
        }
        for (int i = 1; i < 6; i++) {
            table.addRow(ResponseCodeParser.analyzeStatusCode(100 * i + 1), Integer.toString(respCodes[i]));
        }
        LOGGER.info(table);
        writeToFile(table);

    }

    private static void printGeneralInfo(
        String path,
        String fromDate,
        String toDate,
        int totalRequests,
        int totalResponseSize
    ) {


        PrettyTable table = new PrettyTable("Метрика", "значение");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'  'HH:mm:ss");
        table.addRow("Файл(ы)", path);
        table.addRow("Начальная дата", fromDate != null ? dateFormat.format(fromDate) : "-");
        table.addRow("Конечная дата", (toDate != null ? dateFormat.format(toDate) : "-"));
        table.addRow("Количество запросов", Integer.toString(totalRequests));
        table.addRow("Средний размер ответа (в КБ)",
            (totalRequests > 0 ? (totalResponseSize / totalRequests) + "b" : "-"));
        LOGGER.info(table);
        writeToFile(table);


    }

    private static void printResourceStats() {
        LOGGER.info("\n#### Запрашиваемые ресурсы\n");
        PrettyTable table = new PrettyTable("Ресурс", "Количество");
        for (Map.Entry<String, Integer> entry : LogAnalyzer.refererMap.entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        LOGGER.info(table);
        writeToFile(table);

    }

    private static void printResponseCodeStats() {
        LOGGER.info("\n#### Коды ответа\n");
        PrettyTable table = new PrettyTable("Код", "Имя", "Количество");
        for (Map.Entry<Integer, Integer> entry : LogAnalyzer.statusMap.entrySet()) {
            table.addRow(
                Integer.toString(entry.getKey()),
                ResponseCodeParser.getResponseCodeName(Integer.toString(entry.getKey())),
                Integer.toString(entry.getValue())
            );

        }
        LOGGER.info(table);
        writeToFile(table);

    }
}
