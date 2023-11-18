package edu.project3;

import edu.project3.reports.LogReportMarkdown;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class LogReportMarkdownTest {
    @Test
    @DisplayName("format general info table")
    public void formatGeneralInfoTable() {
        Map<String, String> generalInfo = Map.of(
                "files", "file.txt",
                "from", "2023.03.05",
                "to", "2023.03.05",
                "countLogs", "2",
                "avgResponseSize", "20.1"
        );
        String info = """
                #### Общая информация
                | Метрика | Значение |
                |:-:|:-:|
                |       Файл(-ы)        | file.txt |
                |    Начальная дата     | 2023.03.05 |
                |     Конечная дата     | 2023.03.05 |
                |  Количество запросов  | 2 |
                | Средний размер ответа | 20,1 |
                """;
        LogReportMarkdown logReportMarkdown = new LogReportMarkdown();
        Assertions.assertEquals(logReportMarkdown.formatGeneralInfoTable(generalInfo), info);
    }

    @Test
    @DisplayName("format requested resources table")
    public void formatRequestedResourcesTable() {
        Map<String, Long> requestedResourcesInfo = Map.of(
                "resource1", 1L,
                "resource2", 2L,
                "resource3", 2L,
                "resource4", 4L
        );
        LogReportMarkdown logReportMarkdown = new LogReportMarkdown();
        Assertions.assertTrue(requestedResourcesInfo.keySet().stream()
                .allMatch(resource -> logReportMarkdown.formatRequestedResourcesTable(requestedResourcesInfo).contains(String.valueOf(resource))));
        Assertions.assertTrue(requestedResourcesInfo.values().stream()
                .allMatch(count -> logReportMarkdown.formatRequestedResourcesTable(requestedResourcesInfo).contains(String.valueOf(count))));
    }

    @Test
    @DisplayName("format status info table")
    public void formatStatusInfoTable() {
        Map<Integer, Long> statusInfo = Map.of(
                200, 2L,
                304, 3L,
                500, 1L,
                404, 10L
        );
        LogReportMarkdown logReportMarkdown = new LogReportMarkdown();
        Assertions.assertTrue(statusInfo.keySet().stream()
                .allMatch(status -> logReportMarkdown.formatStatusInfoTable(statusInfo).contains(String.valueOf(status))));
        Assertions.assertTrue(statusInfo.values().stream()
                .allMatch(count -> logReportMarkdown.formatStatusInfoTable(statusInfo).contains(String.valueOf(count))));
    }

    @Test
    @DisplayName("format count UserAgent info table")
    public void formatCountUserAgentInfoTable() {
        Map<String, Long> countUserAgentInfo = Map.of(
                "User-Agent1", 3L,
                "User-Agent2", 10L,
                "User-Agent3", 100L,
                "User-Agent4", 23L
        );
        LogReportMarkdown logReportMarkdown = new LogReportMarkdown();
        Assertions.assertTrue(countUserAgentInfo.keySet().stream()
                .allMatch(userAgent -> logReportMarkdown.formatCountUserAgentInfoTable(countUserAgentInfo).contains(String.valueOf(userAgent))));
        Assertions.assertTrue(countUserAgentInfo.values().stream()
                .allMatch(count -> logReportMarkdown.formatCountUserAgentInfoTable(countUserAgentInfo).contains(String.valueOf(count))));
    }

    @Test
    @DisplayName("formatCountRequestTypeInfoTable")
    public void formatCountRequestTypeInfoTable() {
        Map<String, Long> countRequestTypeInfo = Map.of(
                "404", 1L,
                "500", 10L,
                "200", 20L,
                "304", 30L
        );
        LogReportMarkdown logReportMarkdown = new LogReportMarkdown();
        Assertions.assertTrue(countRequestTypeInfo.keySet().stream()
                .allMatch(status -> logReportMarkdown.formatCountRequestTypeInfoTable(countRequestTypeInfo).contains(String.valueOf(status))));
        Assertions.assertTrue(countRequestTypeInfo.values().stream()
                .allMatch(count -> logReportMarkdown.formatCountRequestTypeInfoTable(countRequestTypeInfo).contains(String.valueOf(count))));
    }
}
