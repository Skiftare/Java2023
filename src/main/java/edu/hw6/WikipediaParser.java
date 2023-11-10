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

    public static PortDescription getPortDescription(int portNumber) {
        try {
            String url =
                "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%BF%D0%BE%D1%80%D1%82%D0%BE%D0%B2_TCP_%D0%B8_UDP";
            org.jsoup.nodes.Document document = Jsoup.connect(url).get();

            Elements rows = document.select("table.wikitable tbody tr");
            for (Element row : rows) {
                Elements cells = row.select("td");
                if (cells.size() >= NEED_CELL_SIZE) {
                    String buf = cells.get(0).text();
                    if (!buf.contains(Integer.toString(portNumber)) || buf.split("/").length < 2) {
                        continue;
                    }
                    var portAndProtocol = buf.split("/");
                    String port = portAndProtocol[0];
                    String protocol = portAndProtocol[1];
                    String description = cells.get(1).text();
                    if (port.equals(Integer.toString(portNumber))) {
                        return new PortDescription(port, description, protocol, 0);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return new PortDescription(null, "unknown program", "", 0);
    }
}
