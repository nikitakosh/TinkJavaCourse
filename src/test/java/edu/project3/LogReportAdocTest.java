package edu.project3;

import edu.project3.reports.LogReportAdoc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class LogReportAdocTest {

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
                ==== Общая информация
                |===
                | Метрика | Значение
                | Файл(-ы)             | file.txt
                | Начальная дата       | 2023.03.05
                | Конечная дата        | 2023.03.05
                | Количество запросов  | 2
                | Средний размер ответа| 20.1
                |===
                """;
        LogReportAdoc logReportAdoc = new LogReportAdoc();
        Assertions.assertEquals(logReportAdoc.formatGeneralInfoTable(generalInfo), info);
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
        LogReportAdoc logReportAdoc = new LogReportAdoc();
        Assertions.assertTrue(requestedResourcesInfo.keySet().stream()
                .allMatch(resource -> logReportAdoc.formatRequestedResourcesTable(requestedResourcesInfo).contains(String.valueOf(resource))));
        Assertions.assertTrue(requestedResourcesInfo.values().stream()
                .allMatch(count -> logReportAdoc.formatRequestedResourcesTable(requestedResourcesInfo).contains(String.valueOf(count))));
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
        LogReportAdoc logReportAdoc = new LogReportAdoc();
        Assertions.assertTrue(statusInfo.keySet().stream()
                .allMatch(status -> logReportAdoc.formatStatusInfoTable(statusInfo).contains(String.valueOf(status))));
        Assertions.assertTrue(statusInfo.values().stream()
                .allMatch(count -> logReportAdoc.formatStatusInfoTable(statusInfo).contains(String.valueOf(count))));
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
        LogReportAdoc logReportAdoc = new LogReportAdoc();
        Assertions.assertTrue(countUserAgentInfo.keySet().stream()
                .allMatch(userAgent -> logReportAdoc.formatCountUserAgentInfoTable(countUserAgentInfo).contains(String.valueOf(userAgent))));
        Assertions.assertTrue(countUserAgentInfo.values().stream()
                .allMatch(count -> logReportAdoc.formatCountUserAgentInfoTable(countUserAgentInfo).contains(String.valueOf(count))));
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
        LogReportAdoc logReportAdoc = new LogReportAdoc();
        Assertions.assertTrue(countRequestTypeInfo.keySet().stream()
                .allMatch(status -> logReportAdoc.formatCountRequestTypeInfoTable(countRequestTypeInfo).contains(String.valueOf(status))));
        Assertions.assertTrue(countRequestTypeInfo.values().stream()
                .allMatch(count -> logReportAdoc.formatCountRequestTypeInfoTable(countRequestTypeInfo).contains(String.valueOf(count))));
    }
}
