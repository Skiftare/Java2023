package edu.project3;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("HideUtilityClassConstructor")
public class ResponseCodeParser {
    private static final String HTTP_URL = "https://developer.mozilla.org/ru/docs/Web/HTTP/Status";
    private static final Logger LOGGER = LogManager.getLogger();

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
        Integer needCellSize = 3;
        return columns.size() >= needCellSize && columns.get(0).text().equals(responseCode);
    }

    public static String getResponseCodeName(String responseCode) {
        try {

            Document doc = Jsoup.connect(HTTP_URL).get();
            Elements rows = doc.select("table.standard-table tbody tr");
            for (Element row : rows) {
                if (checkerFowParser(row, responseCode)) {
                    return row.select("td").get(1).text();
                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return "Объяснение для данного кода не найдено";
    }
}
