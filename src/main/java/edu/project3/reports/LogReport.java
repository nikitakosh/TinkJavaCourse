package edu.project3.reports;

import java.util.Map;

public interface LogReport {
    String formatCountRequestTypeInfoTable(Map<String, Long> countRequestTypeInfo);

    String formatCountUserAgentInfoTable(Map<String, Long> countUserAgentInfo);

    String formatGeneralInfoTable(Map<String, String> generalInfo);

    String formatRequestedResourcesTable(Map<String, Long> requestedResourcesInfo);

    String formatStatusInfoTable(Map<Integer, Long> statusInfo);
}
