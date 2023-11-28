package edu.project3;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestContentOfReportInLogAnalyzer {
    <K,V> void thenAssertThatElementInMapExactlyExpectedTimes(K element, V expectedCount, Map<K,V> map){
        assertEquals(expectedCount,map.get(element));
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

        //Проверка ReferMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            "http://www.elasticsearch.org/overview/elkdownloads/",
            6,
            DataClass.getHttpRefererMap()
        );

        // Проверка основного содержимого карты HTTP_USER_AGENT_MAP
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (1.0.1ubuntu2)",
            11830,
            DataClass.getHttpUserAgentMap()
        );
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (0.9.7.9)",
            11365,
            DataClass.getHttpUserAgentMap()
        );

        //Проверка statusMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            304,
            13330,
            DataClass.getStatusMap()
        );
        LogAnalyzer.reset();
    }

    @Test
    @DisplayName("Парсинг с чётко данного пути в системе")
    public void testThatGetPathToOneFileAndReturnsContentOfMetric() {
        //given: full path
        String[] args = {"--path", "src/test/java/edu/project3/resources/log1.txt", "--format", "adoc"};

        //when: parse all logs
        LogAnalyzer.main(args);

        //then: check for some basic content in maps

        //Проверка statusMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            304,
            13334,
            DataClass.getStatusMap()
        );

        // Проверка основного содержимого карты HTTP_USER_AGENT_MAP
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (1.0.1ubuntu2)",
            11830,
            DataClass.getHttpUserAgentMap()
        );
        thenAssertThatElementInMapExactlyExpectedTimes(
            "Debian APT-HTTP/1.3 (0.9.7.9)",
            11365,
            DataClass.getHttpUserAgentMap()
        );

        //Проверка ReferMap
        thenAssertThatElementInMapExactlyExpectedTimes(
            "http://www.elasticsearch.org/overview/elkdownloads/",
            6,
            DataClass.getHttpRefererMap()
        );

        LogAnalyzer.reset();
    }
}
