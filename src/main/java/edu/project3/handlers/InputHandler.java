package edu.project3.handlers;

import edu.project3.exceptions.EmptyArgumentsException;
import edu.project3.exceptions.NotFoundPathException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputHandler {

    public static final String FROM_TIME = "from";
    public static final String TO_TIME = "to";
    public static final String FORMAT = "format";

    public String handleArgs(String[] args) {
        if (args.length == 0) {
            throw new EmptyArgumentsException("empty arguments");
        }
        String userRequest = String.join(" ", args);
        if (!userRequest.matches(".*--path.*")) {
            throw new NotFoundPathException("not found path");
        }
        return userRequest;
    }

    public Map<String, String> getRequestParams(String userRequest) {
        Map<String, String> params = new HashMap<>();
        Pattern pathPattern = Pattern.compile("--path (\\S+)");
        Matcher pathMatcher = pathPattern.matcher(userRequest);
        if (pathMatcher.find()) {
            params.put("path", pathMatcher.group(1));
        }
        Pattern timeFromPattern = Pattern.compile("--from (\\S+)");
        Matcher timeFromMatcher = timeFromPattern.matcher(userRequest);
        if (timeFromMatcher.find()) {
            params.put(FROM_TIME, timeFromMatcher.group(1));
        } else {
            params.put(FROM_TIME, "");
        }
        Pattern timeToPattern = Pattern.compile("--to (\\S+)");
        Matcher timeToMatcher = timeToPattern.matcher(userRequest);
        if (timeToMatcher.find()) {
            params.put(TO_TIME, timeToMatcher.group(1));
        } else {
            params.put(TO_TIME, "");
        }
        Pattern formatPattern = Pattern.compile("--format (\\S+)");
        Matcher formatMatcher = formatPattern.matcher(userRequest);
        if (formatMatcher.find()) {
            params.put(FORMAT, formatMatcher.group(1));
        } else {
            params.put(FORMAT, "");
        }
        return params;
    }
}
