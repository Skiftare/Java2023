package edu.project3.reports;

import edu.project3.systeminteraction.ArgumentsData;
import edu.project3.systeminteraction.DataClass;
import edu.project3.tables.Table;
import edu.project3.utility.UtilityClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import static edu.project3.systeminteraction.DateFormatter.formatDateForLogs;
import static edu.project3.utility.UtilityClass.COUNT_OF_USER_AGENT_ROWS;
import static edu.project3.utility.UtilityClass.TAB_STRING;

public class ReportGenerator {
    private String formatNullableDate(Date date) {
        return (date != null) ? formatDateForLogs(date) : "-";
    }

    public Report generateReport(ReportDataFunction... dataFunctions) {
        Report report = new Report();
        for (ReportDataFunction dataFunction : dataFunctions) {
            dataFunction.generate(report, DataClass.getHttpUserAgentMap());
        }
        return report;
    }

    public ReportDataFunction printFirstExtraMetric() {
        return (report, data) -> {
            Table table = new Table("Метрика дат из логов", "Дата");
            table.nameTable("Первая доп. метрика");
            table.addRow(
                "С какого момента стали поступать логи:",
                formatNullableDate(DataClass.getMinDate())
            );
            table.addRow(
                "До какого момента поступали логи: ",
                formatNullableDate(DataClass.getMaxDate())
            );
            report.addTable(table);
        };
    }

    public ReportDataFunction printSecondExtraMetric() {
        return (report, data) -> {
            Table table = new Table("Статус ответа", "Количество ответов");
            table.nameTable("Вторая доп. метрика");
            int firstNumber;
            int[] respCodes = new int[UtilityClass.getVariationsOfResponseCodes()];
            for (Map.Entry<Integer, Integer> entry : DataClass.getStatusMap().entrySet()) {
                firstNumber = entry.getKey() / UtilityClass.getMultiplierForCodes()
                    - (entry.getKey() % UtilityClass.getMultiplierForCodes() == 0 ? 1 : 0);
                respCodes[firstNumber] += entry.getValue();
            }
            for (int i = 1; i < UtilityClass.getVariationsOfResponseCodes(); i++) {
                table.addRow(
                    ResponseCodeParser.analyzeStatusCode(UtilityClass.getMultiplierForCodes() * i + 1),
                    Integer.toString(respCodes[i])
                );
            }
            report.addTable(table);
        };
    }

    public ReportDataFunction printRemoteUserStats() {
        return (report, data) -> {
            Table table = new Table("REMOTE_USER", "Кол-во запросов");
            table.nameTable("REMOTE_USER stats");
            for (Map.Entry<String, Integer> entry : DataClass.getRemoteUserMap().entrySet()) {
                table.addRow(entry.getKey(), entry.getValue().toString());
            }
            report.addTable(table);
        };
    }

    public ReportDataFunction printHttpUserAgentStats() {
        return (report, data) -> {
            Table table = new Table("HTTP_USER_AGENT_MAP", "Кол-во обращений");
            table.nameTable("Первые 10 самых частых обращений");
            ArrayList<Map.Entry<String, Integer>> listOfMostPopular =
                new ArrayList<>(data.entrySet());
            listOfMostPopular.sort(Map.Entry.comparingByValue());
            int size = Math.min(COUNT_OF_USER_AGENT_ROWS, listOfMostPopular.size());
            for (int i = 0; i < size; i++) {
                Map.Entry<String, Integer> entry = listOfMostPopular.get(i);
                table.addRow(entry.getKey(), entry.getValue().toString());
            }
            report.addTable(table);
        };
    }

    public ReportDataFunction printGeneralInfo() {
        return (report, data) -> {
            Table table = new Table("Метрика", "Значение");
            table.nameTable("Общая информация");
            if (DataClass.getReadedLogs().getFirst().startsWith(UtilityClass.getHttpString())) {
                table.addRow("URL", parseArrayAsOneString(DataClass.getReadedLogs()));
            } else {
                table.addRow("Файл(ы)", parseArrayAsOneString(DataClass.getReadedLogs()));
            }
            table.addRow("Начальная дата", formatNullableDate(ArgumentsData.getFrom()));
            table.addRow("Конечная дата", formatNullableDate(ArgumentsData.getTo()));
            table.addRow("Количество запросов", Integer.toString(DataClass.getTotalRequests()));
            table.addRow(
                "Средний размер ответа (в КБ)",
                (DataClass.getTotalRequests() > 0)
                    ? (DataClass.getTotalResponseSizeByKB() / DataClass.getTotalRequests()) + "KB"
                    : "-"
            );
            report.addTable(table);
        };
    }

    public ReportDataFunction printResourceStats() {
        return (report, data) -> {
            Table table = new Table("Ресурс", "Запросов");
            table.nameTable("Запрашиваемые ресурсы");
            for (Map.Entry<String, Integer> entry : DataClass.getHttpRefererMap().entrySet()) {
                table.addRow(entry.getKey(), entry.getValue().toString());
            }
            report.addTable(table);
        };
    }

    public ReportDataFunction printResponseCodeStats() {
        return (report, data) -> {
            Table table = new Table("Код", "Имя", "Количество");
            table.nameTable("Коды ответа");
            for (Map.Entry<Integer, Integer> entry : DataClass.getStatusMap().entrySet()) {
                table.addRow(
                    Integer.toString(entry.getKey()),
                    ResponseCodeParser.getResponseCodeName(Integer.toString(entry.getKey())),
                    Integer.toString(entry.getValue())
                );
            }
            report.addTable(table);
        };
    }

    private String parseArrayAsOneString(ArrayList<String> incomeArray) {
        StringBuilder sb = new StringBuilder();
        for (String element : incomeArray) {
            sb.append(element).append(TAB_STRING);
        }
        return sb.toString();
    }

    public Report generateReport() {
        return generateReport(
            printGeneralInfo(),
            printResourceStats(),
            printResponseCodeStats(),
            printHttpUserAgentStats(),
            printFirstExtraMetric(),
            printSecondExtraMetric(),
            printRemoteUserStats()
        );
    }
}
