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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LogAnalyzer {
    private static final String LOG_FORMAT = "$remote_addr - - [$time_local] \"$request\" $status $body_bytes_sent \"$http_referer\" \"$http_user_agent\"";
    private static final DateTimeFormatter LOG_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

    static Map<Date, Integer> timeLocalMap = new HashMap<>();
    static Map<String, Integer> adressMap = new HashMap<>();
    static Map<String, Integer> requestMap = new HashMap<>();
    static Map<Integer, Integer> statusMap = new HashMap<>();
    static Map<Integer, Integer> bodyBytesSentMap = new HashMap<>();
    static Map<String, Integer> refererMap = new HashMap<>();
    static Map<String, Integer> browserMap = new HashMap<>();
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
            return (curr.compareTo(from) >= 0) ;
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

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--path":
                    path = args[i + 1];
                    break;
                case "--from":
                    from = convert(args[i + 1]);
                    fromStr = args[i+1];
                    break;
                case "--to":
                    to = convert(args[i + 1]);
                    toStr = args[i+1];
                    break;
                case "--format":
                    format = args[i + 1];
                    break;
            }
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
                put(adressMap, log.getRemoteAddr());
                totalRequests++;
                put(requestMap, log.getRequest());
                put(statusMap, log.getStatus());
                totalResponseSizeByKB+= (log.getBodyBytesSent()/(8*1024));
                //System.out.println(log.getBodyBytesSent());
                put(bodyBytesSentMap, log.getBodyBytesSent());
                put(refererMap, log.getHttpReferer());
                put(agentMap, log.getRemoteUser());
                Date curr = log.getTimeLocal();
                if (isCurrInRange(curr, from,to)) {
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
        /*printResourceStats(resourceCounts, format);
        printResponseCodeStats(responseCodes, format);
        printErrorStats(errorCounts, format);
        printUserActivity(userActivity, format);
        printRefererStats(refererCounts, format);
        //printBrowserStats(browserCounts, format);
        printLocationStats(locationCounts, format);*/
    }


    private static Integer getMaxLength(ArrayList<String> incomeColumn){



        return 0;
    }
    private static void printGeneralInfo(String path, String fromDate, String toDate, int totalRequests, int totalResponseSize, String format) {
        if(format!= null && (format.equals("markdown") || format.equals("adoc"))){
            System.out.println("Все данные, выводимые в консоль, дублируются в файле: output."+(format == "markdown"?"md":".adoc"));
        }
        System.out.println("#### Общая информация\n");

        System.out.println("|        Метрика        |     Значение |");
        System.out.println("|:---------------------:|-------------:|");
        System.out.println("|       Файл(-ы)        | `" + path + "` |");
        System.out.println("|    Начальная дата     | " + (fromDate != null ? String.format(String.valueOf(
            LOG_DATE_FORMATTER)) : "-") + " |");
        System.out.println("|     Конечная дата     | " + (toDate != null ? String.format(String.valueOf(
            LOG_DATE_FORMATTER)) : "-") + " |");
        System.out.println("|  Количество запросов  | " + totalRequests + " |");
        System.out.println("| Средний размер ответа (в КБ) | " + (totalRequests > 0 ? (totalResponseSize / totalRequests) + "b" : "-") + " |\n");
    }

    private static void printResourceStats(Map<String, Integer> resourceCounts, String format) {
        System.out.println("#### Запрашиваемые ресурсы\n");
        System.out.println("|     Ресурс      | Количество |");
        System.out.println("|:---------------:|-----------:|");
        for (Map.Entry<String, Integer> entry : resourceCounts.entrySet()) {
            System.out.println("|  `" + entry.getKey() + "`  | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private static void printResponseCodeStats(Map<Integer, String> responseCodes, String format) {
        System.out.println("#### Коды ответа\n");
        System.out.println("| Код |          Имя          | Количество |");
        System.out.println("|:---:|:---------------------:|-----------:|");
        for (Map.Entry<Integer, String> entry : responseCodes.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private static void printErrorStats(Map<String, Integer> errorCounts, String format) {
        System.out.println("#### Частота ошибок\n");
        System.out.println("|        Ошибка        | Количество |");
        System.out.println("|:--------------------:|-----------:|");
        for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
            System.out.println("|  `" + entry.getKey() + "`  | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private static void printUserActivity(Map<String, Integer> userActivity, String format) {
        System.out.println("#### Статистика по пользователям\n");
        System.out.println("|     Пользователь     | Количество |");
        System.out.println("|:--------------------:|-----------:|");
        for (Map.Entry<String, Integer> entry : userActivity.entrySet()) {
            System.out.println("|  `" + entry.getKey() + "`  | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private static void printRefererStats(Map<String, Integer> refererCounts, String format) {
        System.out.println("#### Статистика по реферерам\n");
        System.out.println("|       Реферер       | Количество |");
        System.out.println("|:------------------:|-----------:|");
        for (Map.Entry<String, Integer> entry : refererCounts.entrySet()) {
            System.out.println("|  `" + entry.getKey() + "`  | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private static void printBrowserStats(Map<String, Integer> browserCounts, String format) {
        System.out.println("#### Статистика по браузерам\n");
        System.out.println("|      Браузер      | Количество |");
        System.out.println("|:-----------------:|-----------:|");
        for (Map.Entry<String, Integer> entry : browserCounts.entrySet()) {
            System.out.println("|  `" + entry.getKey() + "`  | " + entry.getValue() + " |");
        }
        System.out.println();
    }

    private static void printLocationStats(Map<String, Integer> locationCounts, String format) {
        System.out.println("#### Статистика по географическому расположению\n");
        System.out.println("|     Расположение     | Количество |");
        System.out.println("|:--------------------:|-----------:|");
        for (Map.Entry<String, Integer> entry : locationCounts.entrySet()) {
            System.out.println("|  `" + entry.getKey() + "`  | " + entry.getValue() + " |");
        }
        System.out.println();
    }
}
