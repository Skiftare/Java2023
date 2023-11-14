package edu.project3;

import org.junit.jupiter.api.Test;

public class GlobalTest {
    @Test
    public void testLogAnalyzerMain() {
        // Установите нужные вам аргументы
        String[] args = {"--path" ,"https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs", "--format" ,"adoc"};

        // Вызовите метод main из класса LogAnalyzer с нужными аргументами
        LogAnalyzer.main(args);

        // Добавьте дополнительные проверки, если необходимо
        // Например, можно проверить, что определенные строки выводятся в консоль

        // Пример использования AssertJ для проверки вывода в консоль
        // Предположим, что в консоль должна быть выведена строка "Общая информация"
        //assertThat(systemOut().contains("Общая информация")).isTrue();
    }

}
