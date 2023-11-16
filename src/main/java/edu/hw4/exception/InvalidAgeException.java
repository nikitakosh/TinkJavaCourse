package edu.hw4.exception;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String cause) {
        super(cause);
    }
}
