package edu.hw9.task1;

public class InvalidMetricNameException extends RuntimeException {
    public InvalidMetricNameException(String invalidMetricName) {
        super(invalidMetricName);
    }
}
