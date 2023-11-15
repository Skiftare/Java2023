package edu.hw6;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("HideUtilityClassConstructor")
public class WikipediaParser {

    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private final static Integer NEED_CELL_SIZE = 3;
    private final static String URL_WIKI =
        "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%BF%D0%BE%D1%80%D1%82%D0%BE%D0%B2_TCP_%D0%B8_UDP";

    private static boolean checkerFowParser(Element row, int portNumber) {
        Elements cells = row.select("td");

        if (cells.size() >= NEED_CELL_SIZE) {
            return Integer.toString(portNumber).equals(row.select("td").get(0).text().split("/")[0]);
        }
        return false;
    }

    public static PortDescription getPortDescription(int portNumber) {
        try {
            org.jsoup.nodes.Document document = Jsoup.connect(URL_WIKI).get();

            Elements rows = document.select("table.wikitable tbody tr");
            for (Element row : rows) {
                if (checkerFowParser(row, portNumber)) {
                    return new PortDescription(row.select("td").get(1).text());

                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return new PortDescription("unknown program");
    }
}
