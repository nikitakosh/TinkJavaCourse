package edu.hw2.task4;

public class CallingInfoUtils {
    private CallingInfoUtils() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StackTraceElement caller = stackTraceElements[1];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();

        return new CallingInfo(className, methodName);
    }

}
