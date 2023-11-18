package edu.project3.reports;

import edu.project3.parsers.StatusCodeParser;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class LogReportMarkdown implements LogReport {

    public static final String HORIZONTAL_LINE = "|:-:|:-:|\n";
    public static final String STRING_COL_INT_COL = "| %s | %d |\n";

    @Override
    public String formatGeneralInfoTable(Map<String, String> generalInfo) {
        return "#### Общая информация\n"
                + "| Метрика | Значение |\n"
                + HORIZONTAL_LINE
                + String.format("|       Файл(-ы)        | %s |\n", generalInfo.get("files"))
                + String.format("|    Начальная дата     | %s |\n", generalInfo.get("from"))
                + String.format("|     Конечная дата     | %s |\n", generalInfo.get("to"))
                + String.format("|  Количество запросов  | %s |\n", generalInfo.get("countLogs"))
                + String.format("| Средний размер ответа | %s |\n",
                new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)).format(Double.parseDouble(generalInfo.get("avgResponseSize"))));
    }

    @Override
    public String formatRequestedResourcesTable(Map<String, Long> requestedResourcesInfo) {
        StringBuilder requestedResourcesTable = new StringBuilder();
        requestedResourcesTable.append("#### Запрашиваемые ресурсы\n");
        requestedResourcesTable.append("| Ресурс | Количество |\n");
        requestedResourcesTable.append(HORIZONTAL_LINE);
        requestedResourcesInfo.forEach(
                (resource, value) -> requestedResourcesTable.append(String.format(STRING_COL_INT_COL, resource, value))
        );
        return requestedResourcesTable.toString();
    }

    @Override
    public String formatStatusInfoTable(Map<Integer, Long> statusInfo) {
        Map<Integer, String> statusCodes = new StatusCodeParser()
                .parseStatusCode("src/main/resources/project3/statusCodes.txt");
        StringBuilder statusInfoTable = new StringBuilder();
        statusInfoTable.append("#### Коды ответа\n");
        statusInfoTable.append("| Код | Имя | Количество |\n");
        statusInfoTable.append("|:-:|:-:|:-:|\n");
        statusInfo.forEach(
                (status, value) -> statusInfoTable
                        .append(String.format("| %d | %s | %d |\n", status, statusCodes.get(status), value))
        );
        return statusInfoTable.toString();
    }

    @Override
    public String formatCountUserAgentInfoTable(Map<String, Long> countUserAgentInfo) {
        StringBuilder countUserAgentInfoTable = new StringBuilder();
        countUserAgentInfoTable.append("#### Количество User-Agent\n");
        countUserAgentInfoTable.append("| User-Agent | Количество |\n");
        countUserAgentInfoTable.append(HORIZONTAL_LINE);
        countUserAgentInfo.forEach(
                (userAgent, count) -> countUserAgentInfoTable
                        .append(String.format(STRING_COL_INT_COL, userAgent, count))
        );
        return countUserAgentInfoTable.toString();
    }

    @Override
    public String formatCountRequestTypeInfoTable(Map<String, Long> countRequestTypeInfo) {
        StringBuilder countRequestTypeInfoTable = new StringBuilder();
        countRequestTypeInfoTable.append("#### Виды запросов и их количество\n");
        countRequestTypeInfoTable.append("| Вид запроса | Количество |\n");
        countRequestTypeInfoTable.append(HORIZONTAL_LINE);
        countRequestTypeInfo.forEach(
                (requestType, count) -> countRequestTypeInfoTable
                        .append(String.format(STRING_COL_INT_COL, requestType, count))
        );
        return countRequestTypeInfoTable.toString();
    }

}
