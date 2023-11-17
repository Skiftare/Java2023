package edu.project3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    static Map<Date, Integer> timeLocalMap = new HashMap<>();
    static Map<String, Integer> adressMap = new HashMap<>();
    static Map<String, Integer> requestMap = new HashMap<>();
    static Map<Integer, Integer> statusMap = new HashMap<>();
    static Map<Integer, Integer> bodyBytesSentMap = new HashMap<>();
    static Map<String, Integer> refererMap = new HashMap<>();
    static Map<String, Integer> agentMap = new HashMap<>();

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

    private static void writeToFile(String format, String content) {
        String fileName = "output." + format; // Имя файла с расширением в зависимости от формата

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Файл успешно записан.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
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

        int totalRequests = 0;
        int totalResponseSizeByKB = 0;

        try {

            BufferedReader reader;
            assert path != null;
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
                    //System.out.println(log.getBodyBytesSent());
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
        }
        //System.out.println(timeLocalMap);
        //return ;

        //LocalDateTime fromDate = from.isEmpty() ? null : LocalDateTime.parse(from, LOG_DATE_FORMATTER);
        //LocalDateTime toDate = to.isEmpty() ? null : LocalDateTime.parse(to, LOG_DATE_FORMATTER);

        printGeneralInfo(path, fromStr, toStr, totalRequests, totalResponseSizeByKB, format);
        printResourceStats(refererMap, format);
        printResponseCodeStats(statusMap, format);

        printFirstExtraMetric(minDate, maxDate);
        printSecondExtraMetric(statusMap);

    }

    private static void printFirstExtraMetric(Date minDate, Date maxDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'  'HH:mm:ss");
        PrettyTable table = new PrettyTable("Метрика", "значение");
        table.addRow("С какого момента стали поступать логи:", dateFormat.format(minDate));
        table.addRow("До какого момента поступали логи: ", dateFormat.format(maxDate));
        LOGGER.info(table);
    }

    private static void printSecondExtraMetric(Map<Integer, Integer> responseCodes) {
        PrettyTable table = new PrettyTable("Статус ответа", "Количество ответов");
        int[] respCodes = new int[6];
        for (Map.Entry<Integer, Integer> entry : responseCodes.entrySet()) {
            respCodes[entry.getKey() / 100]+=entry.getValue();
        }
        for (int i = 1; i < 6; i++) {
            table.addRow(ResponseCodeParser.analyzeStatusCode(100 * i + 1), Integer.toString(respCodes[i]));
        }
        LOGGER.info(table);
    }

    private static void printGeneralInfo(
        String path,
        String fromDate,
        String toDate,
        int totalRequests,
        int totalResponseSize,
        String format
    ) {

        if (format != null && (format.equals("markdown") || format.equals("adoc"))) {
            System.out.println("Все данные, выводимые в консоль, дублируются в файле: output." +
                (format == "markdown" ? "md" : "adoc"));
        }
        PrettyTable table = new PrettyTable("Метрика", "значение");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'  'HH:mm:ss");
        table.addRow("Файл(ы)", path);
        table.addRow("Начальная дата", fromDate != null ? dateFormat.format(fromDate) : "-");
        table.addRow("Конечная дата", (toDate != null ? dateFormat.format(toDate) : "-"));
        table.addRow("Количество запросов", Integer.toString(totalRequests));
        table.addRow("Средний размер ответа (в КБ)",
            (totalRequests > 0 ? (totalResponseSize / totalRequests) + "b" : "-"));
        LOGGER.info(table);

        /*System.out.println("|        Метрика        |     Значение |");
        System.out.println("|:---------------------:|-------------:|");
        System.out.println("|       Файл(-ы)        | `" + path + "` |");
        //System.out.println("|    Начальная дата     | " + ( + " |");
        //System.out.println("|     Конечная дата     | " +  + " |");
        System.out.println("|  Количество запросов  | " + totalRequests + " |");
        System.out.println("| Средний размер ответа (в КБ) | " + (totalRequests > 0 ? (totalResponseSize / totalRequests) + "b" : "-") + " |\n");
    */
    }

    private static void printResourceStats(Map<String, Integer> resourceCounts, String format) {
        LOGGER.info("\n#### Запрашиваемые ресурсы\n");
        PrettyTable table = new PrettyTable("Ресурс", "Количество");
        for (Map.Entry<String, Integer> entry : resourceCounts.entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        LOGGER.info(table);

    }

    private static void printResponseCodeStats(Map<Integer, Integer> responseCodes, String format) {
        LOGGER.info("\n#### Коды ответа\n");
        PrettyTable table = new PrettyTable("Код", "Имя", "Количество");
        for (Map.Entry<Integer, Integer> entry : responseCodes.entrySet()) {
            table.addRow(
                Integer.toString(entry.getKey()),
                ResponseCodeParser.getResponseCodeName(Integer.toString(entry.getKey())),
                Integer.toString(entry.getValue())
            );

        }
        LOGGER.info(table);
    }
}
