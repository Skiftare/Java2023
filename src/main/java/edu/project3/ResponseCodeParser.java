package edu.project3;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ResponseCodeParser {
    private static String HTTP_URL = "https://developer.mozilla.org/ru/docs/Web/HTTP/Status";
    private static Integer NEED_CELL_SIZE = 3;
    public static String analyzeStatusCode(int statusCode) {
        if (statusCode >= 100 && statusCode < 200) {
            return "Информационный";
        } else if (statusCode >= 200 && statusCode < 300) {
            return "Успешный";
        } else if (statusCode >= 300 && statusCode < 400) {
            return "Перенаправление";
        } else if (statusCode >= 400 && statusCode < 500) {
            return "Клиентская ошибка";
        } else if (statusCode >= 500 && statusCode < 600) {
            return "Серверная ошибка";
        } else {
            return "Неизвестный статус";
        }
    }
    private static boolean checkerFowParser(Element row, String responseCode) {
        Elements columns = row.select("td");
        return columns.size() >= NEED_CELL_SIZE && columns.get(0).text().equals(responseCode);
    }
    public static String getResponseCodeName(String responseCode) {
        try {

            Document doc = Jsoup.connect(HTTP_URL).get();
            Elements rows = doc.select("table.standard-table tbody tr");
            for (Element row : rows) {
                if(checkerFowParser(row, responseCode)) {
                    return row.select("td").get(1).text();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Объяснение для данного кода не найдено";
    }

    // Пример использования
    public static void main(String[] args) {
        String responseCode = "200";
        String responseCodeName = getResponseCodeName(responseCode);
        System.out.println("Код ответа: " + responseCode);
        System.out.println("Объяснение: " + responseCodeName);
    }
}
