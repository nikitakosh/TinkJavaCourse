package edu.project3.reports;

import edu.project3.parsers.StatusCodeParser;
import java.text.DecimalFormat;
import java.util.Map;

public class LogReportAdoc implements LogReport {

    public static final String HORIZONTAL_LINE = "|===\n";
    public static final String VERTICAL_LINE = " | ";

    @Override
    public String formatGeneralInfoTable(Map<String, String> generalInfo) {
        return "==== Общая информация\n"
                + HORIZONTAL_LINE
                + "| Метрика | Значение\n"
                + "| Файл(-ы)             | " + generalInfo.get("files") + "\n"
                + "| Начальная дата       | " + generalInfo.get("from") + "\n"
                + "| Конечная дата        | " + generalInfo.get("to") + "\n"
                + "| Количество запросов  | " + generalInfo.get("countLogs") + "\n"
                + "| Средний размер ответа| "
                + new DecimalFormat("#.##").format(Double.parseDouble(generalInfo.get("avgResponseSize"))) + "\n"
                + HORIZONTAL_LINE;
    }

    @Override
    public String formatRequestedResourcesTable(Map<String, Long> requestedResourcesInfo) {
        StringBuilder requestedResourcesTable = new StringBuilder();
        requestedResourcesTable.append("==== Запрашиваемые ресурсы\n");
        requestedResourcesTable.append(HORIZONTAL_LINE);
        requestedResourcesTable.append("| Ресурс | Количество\n");
        requestedResourcesInfo.forEach(
                (resource, value) -> requestedResourcesTable.append("| ")
                        .append(resource)
                        .append(VERTICAL_LINE)
                        .append(value)
                        .append("\n")
        );
        requestedResourcesTable.append(HORIZONTAL_LINE);
        return requestedResourcesTable.toString();
    }

    @Override
    public String formatStatusInfoTable(Map<Integer, Long> statusInfo) {
        Map<Integer, String> statusCodes = new StatusCodeParser()
                .parseStatusCode("src/main/resources/project3/statusCodes.txt");
        StringBuilder statusInfoTable = new StringBuilder();
        statusInfoTable.append("==== Коды ответа\n");
        statusInfoTable.append(HORIZONTAL_LINE);
        statusInfoTable.append("| Код | Имя | Количество\n");
        statusInfo.forEach(
                (status, value) -> statusInfoTable.append("| ")
                        .append(status)
                        .append(String.format(" | %s | ", statusCodes.get(status)))
                        .append(value)
                        .append("\n")
        );
        statusInfoTable.append(HORIZONTAL_LINE);
        return statusInfoTable.toString();
    }

    @Override
    public String formatCountUserAgentInfoTable(Map<String, Long> countUserAgentInfo) {
        StringBuilder countUserAgentInfoTable = new StringBuilder();
        countUserAgentInfoTable.append("==== Количество User-Agent\n");
        countUserAgentInfoTable.append(HORIZONTAL_LINE);
        countUserAgentInfoTable.append("| User-Agent | Количество\n");
        countUserAgentInfo.forEach(
                (userAgent, count) -> countUserAgentInfoTable.append("| ")
                        .append(userAgent)
                        .append(VERTICAL_LINE)
                        .append(count)
                        .append("\n")
        );
        countUserAgentInfoTable.append(HORIZONTAL_LINE);
        return countUserAgentInfoTable.toString();
    }

    @Override
    public String formatCountRequestTypeInfoTable(Map<String, Long> countRequestTypeInfo) {
        StringBuilder countRequestTypeInfoTable = new StringBuilder();
        countRequestTypeInfoTable.append("==== Виды запросов и их количество\n");
        countRequestTypeInfoTable.append(HORIZONTAL_LINE);
        countRequestTypeInfoTable.append("| Вид запроса | Количество\n");
        countRequestTypeInfo.forEach(
                (requestType, count) -> countRequestTypeInfoTable.append("| ")
                        .append(requestType)
                        .append(VERTICAL_LINE)
                        .append(count)
                        .append("\n")
        );
        countRequestTypeInfoTable.append(HORIZONTAL_LINE);
        return countRequestTypeInfoTable.toString();
    }

}
