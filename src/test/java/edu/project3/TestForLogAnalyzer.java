package edu.project3;

import edu.project3.systeminteraction.DataClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForLogAnalyzer {

    @BeforeEach
    public void resetAll() {
        LogAnalyzer.reset();
    }

    void thenCheckTimeLocalMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckRemoteAddrMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckRequestMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckStatusMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckBodyBytesSentMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckHttpRefererMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckRemoteUserMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckHttpUserAgentMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }
    static void thenAssertThatElementInHttpUserAgentMapExistExpectedTimes(String element, int expected){
        assertEquals(expected, DataClass.getHttpUserAgentMap().get(element));
    }

    static void thenAssertThatElementInHttpRefererMapExistExpectedTimes(String element, int expected){
        assertEquals(expected,DataClass.getHttpRefererMap().get(element));
    }

    static void thenAssertThatElementInStatusMapExistExpectedTimes(int element, int expected){
        assertEquals(expected,DataClass.getStatusMap().get(element));
    }

    @Nested
    class testOfMapSize {
        @Test
        @DisplayName("Парсинг с сайта")
        public void testThatGetHttpAndReturnsMetricFile() {
            //given: http-server & format
            String[] args = {"--path",
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                "--format", "adoc"};

            //when: parse all logs
            LogAnalyzer.main(args);

            //then: check for same sizes in all maps
            thenCheckTimeLocalMap(22467, DataClass.getTimeLocalMap().size());
            thenCheckRemoteAddrMap(2660, DataClass.getRemoteAddrMap().size());
            thenCheckRequestMap(5, DataClass.getRequestMap().size());
            thenCheckStatusMap(6, DataClass.getStatusMap().size());
            thenCheckBodyBytesSentMap(187, DataClass.getBodyBytesSentMap().size());
            thenCheckHttpRefererMap(8, DataClass.getHttpRefererMap().size());
            thenCheckRemoteUserMap(1, DataClass.getRemoteUserMap().size());
            thenCheckHttpUserAgentMap(136, DataClass.getHttpUserAgentMap().size());

        }

        @Test
        @DisplayName("Парсинг с чётко данного пути в системе")
        public void testThatGetPathToOneFileAndReturnsMetric() {
            //given: full path
            String[] args = {"--path", "src/test/java/edu/project3/resources/log1.txt", "--format", "adoc"};

            //when: parse all logs
            LogAnalyzer.main(args);

            thenCheckTimeLocalMap(22467, DataClass.getTimeLocalMap().size());
            thenCheckRemoteAddrMap(2660, DataClass.getRemoteAddrMap().size());
            thenCheckRequestMap(5, DataClass.getRequestMap().size());
            thenCheckStatusMap(6, DataClass.getStatusMap().size());
            thenCheckBodyBytesSentMap(187, DataClass.getBodyBytesSentMap().size());
            thenCheckHttpRefererMap(8, DataClass.getHttpRefererMap().size());
            thenCheckRemoteUserMap(4, DataClass.getRemoteUserMap().size());
            thenCheckHttpUserAgentMap(136, DataClass.getHttpUserAgentMap().size());

        }

        @Test
        @DisplayName("Парсинг всего и вся из папки, но указан интервал времени так, что логов нет")
        public void testThatGetFolderAndReturnedEmptyMaps() {
            //given: path, format & date interval, but date interval is bad
            String[] args =
                {"--path", "src/test/java/edu/project3/resources/", "--format", "markdown", "--from", "2023-08-31",
                    "--to",
                    "2023-09-24"};

            //when: parse all logs
            LogAnalyzer.main(args);

            //then: check for same sizes in all maps
            thenCheckTimeLocalMap(0, DataClass.getTimeLocalMap().size());
            thenCheckRemoteAddrMap(0, DataClass.getRemoteAddrMap().size());
            thenCheckRequestMap(0, DataClass.getRequestMap().size());
            thenCheckStatusMap(0, DataClass.getStatusMap().size());
            thenCheckBodyBytesSentMap(0, DataClass.getBodyBytesSentMap().size());
            thenCheckHttpRefererMap(0, DataClass.getHttpRefererMap().size());
            thenCheckRemoteUserMap(0, DataClass.getRemoteUserMap().size());
            thenCheckHttpUserAgentMap(0, DataClass.getHttpUserAgentMap().size());

        }

        @Test
        @DisplayName("Парсинг всего и вся из папки, но интервал позволяет брать логи")
        public void testThatGetFolderAndReturnedParsedFileAsLogs() {
            //given: path, format & date interval, but date interval is bad
            String[] args =
                {"--path", "src/test/java/edu/project3/resources/", "--format", "markdown", "--from", "2003-08-31",
                    "--to",
                    "2023-09-24"};

            //when: parse all logs
            LogAnalyzer.main(args);

            //then: check for same sizes in all maps
            thenCheckTimeLocalMap(22467, DataClass.getTimeLocalMap().size());
            thenCheckRemoteAddrMap(2660, DataClass.getRemoteAddrMap().size());
            thenCheckRequestMap(5, DataClass.getRequestMap().size());
            thenCheckStatusMap(6, DataClass.getStatusMap().size());
            thenCheckBodyBytesSentMap(187, DataClass.getBodyBytesSentMap().size());
            thenCheckHttpRefererMap(8, DataClass.getHttpRefererMap().size());
            thenCheckRemoteUserMap(4, DataClass.getRemoteUserMap().size());
            thenCheckHttpUserAgentMap(136, DataClass.getHttpUserAgentMap().size());

        }

        @Test
        @DisplayName(
            "Дан путь через glob. Находим два файла, удовлетворяющие условию, считываем. Интервал времени хороший")
        public void testThatGetFolderWithMissingsAndFinalFileNameAndReturnedParsedFileAsLogs() {
            //given: path, format & date interval, but date interval is good
            String[] args =
                {"--path", "src/test/java/edu/project3/resources/**/log1.txt", "--from", "2000-08-31", "--format",
                    "markdown", "--to", "2029-09-24"};

            //when: parse all logs
            LogAnalyzer.main(args);

            //then: check for same sizes in all maps
            thenCheckTimeLocalMap(22467, DataClass.getTimeLocalMap().size());
            thenCheckRemoteAddrMap(2660, DataClass.getRemoteAddrMap().size());
            thenCheckRequestMap(5, DataClass.getRequestMap().size());
            thenCheckStatusMap(6, DataClass.getStatusMap().size());
            thenCheckBodyBytesSentMap(187, DataClass.getBodyBytesSentMap().size());
            thenCheckHttpRefererMap(8, DataClass.getHttpRefererMap().size());
            thenCheckRemoteUserMap(4, DataClass.getRemoteUserMap().size());
            thenCheckHttpUserAgentMap(136, DataClass.getHttpUserAgentMap().size());
        }
    }

    @Nested
    class testOfContent {

        @Test
        @DisplayName("Парсинг с сайта")
        public void testThatGetHttpAndReturnsContentOfMetricFile() {
            //given: http-server & format
            String[] args = {"--path",
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                "--format", "adoc"};

            //when: parse all logs
            LogAnalyzer.main(args);

            //then: check for some basic content in maps

            // Проверка основного содержимого карты HTTP_USER_AGENT_MAP
            thenAssertThatElementInHttpUserAgentMapExistExpectedTimes(
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)",
                11830
            );
            thenAssertThatElementInHttpUserAgentMapExistExpectedTimes(
                "Debian APT-HTTP/1.3 (0.9.7.9)",
                11365
            );

            //Проверка ReferMap
            thenAssertThatElementInHttpRefererMapExistExpectedTimes(
                "http://www.elasticsearch.org/overview/elkdownloads/",
                6
            );

            //Проверка statusMap
            thenAssertThatElementInStatusMapExistExpectedTimes(
                304,
                13330
            );
        }

        @Test
        @DisplayName("Парсинг с чётко данного пути в системе")
        public void testThatGetPathToOneFileAndReturnsContentOfMetric() {
            //given: full path
            String[] args = {"--path", "src/test/java/edu/project3/resources/log1.txt", "--format", "adoc"};

            //when: parse all logs
            LogAnalyzer.main(args);

            //then: check for some basic content in maps

            // Проверка основного содержимого карты HTTP_USER_AGENT_MAP
            thenAssertThatElementInHttpUserAgentMapExistExpectedTimes(
                "Debian APT-HTTP/1.3 (1.0.1ubuntu2)",
                11830
            );
            thenAssertThatElementInHttpUserAgentMapExistExpectedTimes(
                "Debian APT-HTTP/1.3 (0.9.7.9)",
                11365
            );

            //Проверка ReferMap
            thenAssertThatElementInHttpRefererMapExistExpectedTimes(
                "http://www.elasticsearch.org/overview/elkdownloads/",
                6
            );

            //Проверка statusMap
            thenAssertThatElementInStatusMapExistExpectedTimes(
                304,
                13334
            );
        }
    }
}
