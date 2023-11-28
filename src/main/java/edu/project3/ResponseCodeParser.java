package edu.project3;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static edu.project3.utility.UtilityClass.CLIENT_ERROR_MAX;
import static edu.project3.utility.UtilityClass.CLIENT_ERROR_MIN;
import static edu.project3.utility.UtilityClass.HTTP_URL;
import static edu.project3.utility.UtilityClass.INFORMATIONAL_MAX;
import static edu.project3.utility.UtilityClass.INFORMATIONAL_MIN;
import static edu.project3.utility.UtilityClass.NEEDED_CELL_SIZE;
import static edu.project3.utility.UtilityClass.REDIRECTION_MAX;
import static edu.project3.utility.UtilityClass.REDIRECTION_MIN;
import static edu.project3.utility.UtilityClass.SERVER_ERROR_MAX;
import static edu.project3.utility.UtilityClass.SERVER_ERROR_MIN;
import static edu.project3.utility.UtilityClass.SUCCESSFUL_MAX;
import static edu.project3.utility.UtilityClass.SUCCESSFUL_MIN;

@SuppressWarnings("HideUtilityClassConstructor")
public class ResponseCodeParser {

    public static String analyzeStatusCode(int statusCode) {
        String result;

        if (statusCode >= INFORMATIONAL_MIN && statusCode < INFORMATIONAL_MAX) {
            result = "Информационный";
        } else if (statusCode >= SUCCESSFUL_MIN && statusCode < SUCCESSFUL_MAX) {
            result = "Успешный";
        } else if (statusCode >= REDIRECTION_MIN && statusCode < REDIRECTION_MAX) {
            result = "Перенаправление";
        } else if (statusCode >= CLIENT_ERROR_MIN && statusCode < CLIENT_ERROR_MAX) {
            result = "Клиентская ошибка";
        } else if (statusCode >= SERVER_ERROR_MIN && statusCode < SERVER_ERROR_MAX) {
            result = "Серверная ошибка";
        } else {
            result = "Неизвестный статус";
        }

        return result;
    }

    private static boolean checkerFowParser(Element row, String responseCode) {
        Elements columns = row.select("td");

        return columns.size() >= NEEDED_CELL_SIZE && columns.get(0).text().equals(responseCode);
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
                    return secondCell.text();
                }
            }
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
        return "Объяснение для данного кода не найдено";
    }
}
