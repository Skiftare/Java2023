package edu.project3;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("HideUtilityClassConstructor")
public class ResponseCodeParser {
    private static final String HTTP_URL = "https://developer.mozilla.org/ru/docs/Web/HTTP/Status";

    @SuppressWarnings("MagicNumber")
    public static String analyzeStatusCode(int statusCode) {
        String result;
        if (statusCode >= 100 && statusCode < 200) {
            result = "Информационный";
        } else if (statusCode >= 200 && statusCode < 300) {
            result = "Успешный";
        } else if (statusCode >= 300 && statusCode < 400) {
            result = "Перенаправление";
        } else if (statusCode >= 400 && statusCode < 500) {
            result = "Клиентская ошибка";
        } else if (statusCode >= 500 && statusCode < 600) {
            result = "Серверная ошибка";
        } else {
            result = "Неизвестный статус";
        }
        return result;
    }

    @SuppressWarnings("MagicNumber")
    private static boolean checkerFowParser(Element row, String responseCode) {
        Elements columns = row.select("td");
        int needCellSize = 3;
        return columns.size() >= needCellSize && columns.get(0).text().equals(responseCode);
    }

    public static String getResponseCodeName(String responseCode) {
        try { //Тут падает checkstyle, если пустую строчку оставить
            Document doc = Jsoup.connect(HTTP_URL).get();
            String chooseAllTrInTbodyAtElementTableWithClassStandartTable = "table.standard-table tbody tr";

            Elements rows = doc.select(chooseAllTrInTbodyAtElementTableWithClassStandartTable);
            for (Element row : rows) {
                if (checkerFowParser(row, responseCode)) {
                    Elements cells = row.select("td");
                    Element secondCell = cells.get(1);
                    String cellText = secondCell.text();
                    return cellText;
                }
            }
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
        return "Объяснение для данного кода не найдено";
    }
}
