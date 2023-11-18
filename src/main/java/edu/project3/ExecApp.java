package edu.project3;

import edu.project3.handlers.InputHandler;
import edu.project3.handlers.PathHandler;
import edu.project3.parsers.LogParser;
import edu.project3.reports.LogReportAdoc;
import edu.project3.reports.LogReportMarkdown;
import edu.project3.utils.WriteUtils;
import java.util.List;
import java.util.Map;

public class ExecApp {


    public void run(String[] args) {
        InputHandler inputHandler = new InputHandler();
        Map<String, String> params = inputHandler.getRequestParams(inputHandler.handleArgs(args));
        PathHandler pathHandler = new PathHandler();
        LogParser logParser = pathHandler.handlePath(params.get("path"));
        List<LogRecord> logRecords = logParser.parseLogRecord();
        LogsAnalyzer logsAnalyzer = new LogsAnalyzer(params, logRecords);
        if (params.get("format").equals("adoc")) {
            LogReportAdoc logReportAdoc = new LogReportAdoc();
            WriteUtils.writeInAdoc(
                    logReportAdoc.formatGeneralInfoTable(logsAnalyzer.generalInfo()) + "\n"
                            + logReportAdoc.formatRequestedResourcesTable(logsAnalyzer.requestedResourcesInfo()) + "\n"
                            + logReportAdoc.formatStatusInfoTable(logsAnalyzer.statusInfo()) + "\n"
                            + logReportAdoc.formatCountUserAgentInfoTable(logsAnalyzer.countUserAgentInfo()) + "\n"
                            + logReportAdoc.formatCountRequestTypeInfoTable(logsAnalyzer.countRequestTypeInfo())
            );
        } else {
            LogReportMarkdown logReportMarkdown = new LogReportMarkdown();
            WriteUtils.writeInMarkdown(
                    logReportMarkdown.formatGeneralInfoTable(logsAnalyzer.generalInfo()) + "\n"
                            + logReportMarkdown
                            .formatRequestedResourcesTable(logsAnalyzer.requestedResourcesInfo()) + "\n"
                            + logReportMarkdown.formatStatusInfoTable(logsAnalyzer.statusInfo()) + "\n"
                            + logReportMarkdown.formatCountUserAgentInfoTable(logsAnalyzer.countUserAgentInfo()) + "\n"
                            + logReportMarkdown.formatCountRequestTypeInfoTable(logsAnalyzer.countRequestTypeInfo())
            );
        }
    }
}
