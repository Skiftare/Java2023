package edu.project3;

import edu.project3.utility.UtilityClass;
import java.util.ArrayList;
import java.util.Map;
import static edu.project3.DateFormatter.formatDateForLogs;

public class ReportGenerator {
    private static Table printFirstExtraMetric() {

        Table table = new Table("Метрика дат из логов", "Дата");
        table.nameTable("Первая доп. метрика");
        table.addRow(
            "С какого момента стали поступать логи:",
            DataClass.getMinDate() == null ? "-" : formatDateForLogs(DataClass.getMinDate())
        );
        table.addRow(
            "До какого момента поступали логи: ",
            DataClass.getMaxDate() == null ? "-" : formatDateForLogs(DataClass.getMaxDate())
        );
        return table;
    }

    private static Table printSecondExtraMetric() {
        Table table = new Table("Статус ответа", "Количество ответов");
        table.nameTable("Вторая доп. метрика");
        int firstNumber;
        int[] respCodes = new int[UtilityClass.getVariationsOfResponseCodes()];
        for (Map.Entry<Integer, Integer> entry : DataClass.getStatusMap().entrySet()) {
            firstNumber = entry.getKey() / UtilityClass.getMultiplierForCodes() - (entry.getKey() %  UtilityClass.getMultiplierForCodes() == 0 ? 1 : 0);
            respCodes[firstNumber] += entry.getValue();
        }
        for (int i = 1; i < UtilityClass.getVariationsOfResponseCodes(); i++) {

            table.addRow(
                ResponseCodeParser.analyzeStatusCode( UtilityClass.getMultiplierForCodes() * i + 1),
                Integer.toString(respCodes[i])
            );
        }
        return table;
    }

    private static Table printRemoteUserStats() {
        Table table = new Table("REMOTE_USER", "Кол-во запросов");
        table.nameTable("REMOTE_USER stats");
        for (Map.Entry<String, Integer> entry : DataClass.getRemoteUserMap().entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        return table;
    }
    private static Table printHttpUserAgentStats(){

        Table table = new Table("HTTP_USER_AGENT_MAP", "Кол-во обращений");
        for (Map.Entry<String, Integer> entry : DataClass.getHttpUserAgentMap().entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        return table;
    }

    private static String parseArrayAsOneString(ArrayList<String> incomeArray) {
        StringBuilder sb = new StringBuilder();
        for (String element : incomeArray) {
            sb.append(element).append('\t');
        }
        return sb.toString();
    }

    private static Table printGeneralInfo() {

        Table table = new Table("Метрика", "Значение");
        table.nameTable("Общая информация");
        if (DataClass.getReadedLogs().get(0).startsWith(UtilityClass.getHttpString())) {
            table.addRow("URL", parseArrayAsOneString(DataClass.getReadedLogs()));
        } else {
            table.addRow("Файл(ы)", parseArrayAsOneString(DataClass.getReadedLogs()));
        }
        table.addRow("Начальная дата", (ArgumentsData.getFrom() != null ? ArgumentsData.getFromStr() : "-"));
        table.addRow("Конечная дата", (ArgumentsData.getTo() != null ? ArgumentsData.getToStr() : "-"));
        table.addRow("Количество запросов", Integer.toString(DataClass.getTotalRequests()));
        table.addRow(
            "Средний размер ответа (в КБ)",
            (DataClass.getTotalRequests()) > 0 ?
                (DataClass.getTotalResponseSizeByKB() / DataClass.getTotalRequests()) +
                    "KB" : "-"
        );
        return table;

    }

    private static Table printResourceStats() {
        Table table = new Table("Ресурс", "Запросов");
        table.nameTable("Запрашиваемые ресурсы");
        for (Map.Entry<String, Integer> entry : DataClass.getHttpRefererMap().entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        return table;
    }

    private static Table printResponseCodeStats() {
        Table table = new Table("Код", "Имя", "Количество");
        table.nameTable("Коды ответа");
        for (Map.Entry<Integer, Integer> entry : DataClass.getStatusMap().entrySet()) {
            table.addRow(
                Integer.toString(entry.getKey()),
                ResponseCodeParser.getResponseCodeName(Integer.toString(entry.getKey())),
                Integer.toString(entry.getValue())
            );
        }
        return table;

    }
    public static Report generateReport(){
        Report report = new Report();
        report.addTable(printGeneralInfo());
        report.addTable(printResourceStats());
        report.addTable(printFirstExtraMetric());
        report.addTable(printResponseCodeStats());
        report.addTable(printHttpUserAgentStats());
        return report;
    }
}
