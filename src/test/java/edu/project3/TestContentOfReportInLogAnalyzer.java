package edu.project3;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestContentOfReportInLogAnalyzer {
    <T,K> void thenAssertThatElementInMapExactlyExpectedTimes(T element, K expectedCount, Map<T,K> map){
        assertEquals(expectedCount,map.get(element));
    }
    @BeforeEach
    public void resetAll() {
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
        Map<String, Integer> httpUserAgentMap = DataClass.getHttpUserAgentMap();
        Map<String,Integer> httpReferMap = DataClass.getHttpRefererMap();
        Map<Integer, Integer> statusMap = DataClass.getStatusMap();

        // Проверка основного содержимого карты HTTP_USER_AGENT_MAP
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (1.0.1ubuntu2)",
            11830,
            httpUserAgentMap
        );
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (0.9.7.9)",
            11365,
            httpUserAgentMap
        );

        //Проверка ReferMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            "http://www.elasticsearch.org/overview/elkdownloads/",
        6,
            httpReferMap
        );

        //Проверка statusMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            304,
            13330,
            statusMap
        );
    }

    @Test
    @DisplayName("Парсинг с чётко данного пути в системе")
    public void testThatGetPathToOneFileAndReturnsContentOfMetric() {
        //given: http-server & format
        String[] args = {"--path", "src/test/java/edu/project3/resources/log1.txt", "--format", "markdown"};

        //when: parse all logs
        LogAnalyzer.main(args);

        //then: check for some basic content in maps
        Map<String, Integer> httpUserAgentMap = DataClass.getHttpUserAgentMap();
        Map<String,Integer> httpReferMap = DataClass.getHttpRefererMap();
        Map<Integer, Integer> statusMap = DataClass.getStatusMap();

        // Проверка основного содержимого карты HTTP_USER_AGENT_MAP
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (1.0.1ubuntu2)",
            11830,
            httpUserAgentMap
        );
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (0.9.7.9)",
            11365,
            httpUserAgentMap
        );

        //Проверка ReferMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            "http://www.elasticsearch.org/overview/elkdownloads/",
            6,
            httpReferMap
        );

        //Проверка statusMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            304,
            13334,
            statusMap
        );
    }
}
