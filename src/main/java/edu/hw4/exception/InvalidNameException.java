package edu.hw4.exception;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String cause) {
        super(cause);
    }
}
