package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForLogAnalyzer {

    @BeforeEach
    public void resetAll() {
        DataClass.resetStaticVariables();
    }

    void thenCheckTimeLocalMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, actualSize);
    }

    void thenCheckRemoteAddrMap(int expectedSize, int actualSize) {
        assertEquals(expectedSize, DataClass.getRemoteAddrMap().size());
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

    @Test
    @DisplayName("Парсинг с сайта")
    public void testThatGetHttpAndReturnsMetricFile() {
        //given: http-server & format
        String[] args = {"--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--format", "adoc"};

        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
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
    public void testThatGetPathToOneFileAndReturnsMetric() throws IOException {
        //given: full path
        String[] args = {"--path", "src/test/java/edu/project3/resources/log1.txt", "--format", "adoc"};

        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
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
            {"--path", "src/test/java/edu/project3/resources/", "--format", "markdown", "--from", "2023-08-31", "--to",
                "2023-09-24"};

        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
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
            {"--path", "src/test/java/edu/project3/resources/", "--format", "markdown", "--from", "2003-08-31", "--to",
                "2023-09-24"};

        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
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
    @DisplayName("Дан путь через glob. Находим два файла, удовлетворяющие условию, считываем. Интервал времени хороший")
    public void testThatGetFolderWithMissingsAndFinalFileNameAndReturnedParsedFileAsLogs() {
        //given: path, format & date interval, but date interval is good
        String[] args =
            {"--path", "src/test/java/edu/project3/resources/**/log1.txt", "--from", "2000-08-31", "--format",
                "markdown", "--to", "2029-09-24"};

        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
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
    //TODO: we checked by the count. But we didn't checked amount (must be x2, we can see it in files)
}
