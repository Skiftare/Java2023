package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestContentOfReportInLogAnalyzer {
    static void thenAssertThatElementInHttpUserAgentMapExistExpectedTimes(String element, int expected){
        assertEquals(expected,DataClass.getHttpUserAgentMap().get(element));
    }

    static void thenAssertThatElementInHttpRefererMapExistExpectedTimes(String element, int expected){
        assertEquals(expected,DataClass.getHttpRefererMap().get(element));
    }

    static void thenAssertThatElementInStatusMapExistExpectedTimes(int element, int expected){
        assertEquals(expected,DataClass.getStatusMap().get(element));
    }

    @BeforeEach
    void resetAll(){
        LogAnalyzer.reset();
    }

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
