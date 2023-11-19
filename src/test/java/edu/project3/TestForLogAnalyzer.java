package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForLogAnalyzer {

    @BeforeEach
    public void resetAll(){
        LogAnalyzer.resetStaticVariables();
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
        assertEquals(22467, logAnalyzer.getTimeLocalMap().size());
        assertEquals(2660, logAnalyzer.getRemoteAddrMap().size());
        assertEquals(5, logAnalyzer.getRequestMap().size());
        assertEquals(6, logAnalyzer.getStatusMap().size());
        assertEquals(187, logAnalyzer.getBodyBytesSentMap().size());
        assertEquals(8, logAnalyzer.getHttpRefererMap().size());
        assertEquals(1, logAnalyzer.getRemoteUserMap().size());
        assertEquals (136, logAnalyzer.getHttpUserAgentMap().size());
    }

    @Test
    @DisplayName("Парсинг с чётко данного пути в системе")
    public void testThatGetPathToOneFileAndReturnsMetric() {
        //given: full path
        String[] args = {"--path", "src/test/java/edu/project3/resources/log1.txt","--format", "adoc"};
        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        LogAnalyzer.main(args);
        //then: check for same sizes in all maps
        assertEquals(22467, logAnalyzer.getTimeLocalMap().size());
        assertEquals(2660, logAnalyzer.getRemoteAddrMap().size());
        assertEquals(5, logAnalyzer.getRequestMap().size());
        assertEquals(6, logAnalyzer.getStatusMap().size());
        assertEquals(187, logAnalyzer.getBodyBytesSentMap().size());
        assertEquals(8, logAnalyzer.getHttpRefererMap().size());
        assertEquals(4, logAnalyzer.getRemoteUserMap().size());
        assertEquals (136, logAnalyzer.getHttpUserAgentMap().size());

    }

    @Test
    @DisplayName("Парсинг всего и вся из папки, но указан интервал времени так, что логов нет")
    public void testThatGetFolderAndReturnedEmptyMaps() {
        //given: path, format & date interval, but date interval is bad
        String[] args = {"--path", "src/test/java/edu/project3/resources/", "--format", "markdown", "--from", "2023-08-31", "--to", "2023-09-24"};
        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        LogAnalyzer.main(args);
        //then: check for same sizes in all maps
        assertEquals(0, logAnalyzer.getTimeLocalMap().size());
        assertEquals(0, logAnalyzer.getRemoteAddrMap().size());
        assertEquals(0, logAnalyzer.getRequestMap().size());
        assertEquals(0, logAnalyzer.getStatusMap().size());
        assertEquals(0, logAnalyzer.getBodyBytesSentMap().size());
        assertEquals(0, logAnalyzer.getHttpRefererMap().size());
        assertEquals(0, logAnalyzer.getHttpUserAgentMap().size());
        assertEquals(0, logAnalyzer.getRemoteUserMap().size());
        assertEquals (0, logAnalyzer.getHttpUserAgentMap().size());

    }
    @Test
    @DisplayName("Парсинг всего и вся из папки, но интервал позволяет брать логи")
    public void testThatGetFolderAndReturnedParsedFileAsLogs() {
        //given: path, format & date interval, but date interval is bad
        String[] args = {"--path", "src/test/java/edu/project3/resources/", "--format", "markdown", "--from", "2003-08-31", "--to", "2023-09-24"};
        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        LogAnalyzer.main(args);
        //then: check for same sizes in all maps
        assertEquals(22467, logAnalyzer.getTimeLocalMap().size());
        assertEquals(22467, logAnalyzer.getTimeLocalMap().size());
        assertEquals(2660, logAnalyzer.getRemoteAddrMap().size());
        assertEquals(5, logAnalyzer.getRequestMap().size());
        assertEquals(6, logAnalyzer.getStatusMap().size());
        assertEquals(187, logAnalyzer.getBodyBytesSentMap().size());
        assertEquals(8, logAnalyzer.getHttpRefererMap().size());
        assertEquals(136, logAnalyzer.getHttpUserAgentMap().size());
        assertEquals(4, logAnalyzer.getRemoteUserMap().size());

    }

    @Test
    @DisplayName("Дан путь через glob. Находим два файла, удовлетворяющие условию, считываем. Интервал времени хороший")
    public void testThatGetFolderWithMissingsAndFinalFileNameAndReturnedParsedFileAsLogs() {
        //given: path, format & date interval, but date interval is good
        String[] args = {"--path", "src/test/java/edu/project3/resources/**/log1.txt", "--from", "2000-08-31","--format", "markdown", "--to", "2029-09-24"};
        //when: parse all logs
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        LogAnalyzer.main(args);
        //then: check for same sizes in all maps
        assertEquals(22467, logAnalyzer.getTimeLocalMap().size());
        assertEquals(22467, logAnalyzer.getTimeLocalMap().size());
        assertEquals(2660, logAnalyzer.getRemoteAddrMap().size());
        assertEquals(5, logAnalyzer.getRequestMap().size());
        assertEquals(6, logAnalyzer.getStatusMap().size());
        assertEquals(187, logAnalyzer.getBodyBytesSentMap().size());
        assertEquals(8, logAnalyzer.getHttpRefererMap().size());
        assertEquals(136, logAnalyzer.getHttpUserAgentMap().size());
    }

}
