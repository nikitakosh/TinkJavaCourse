package edu.project1.exceptions;

public class InvalidWordGivenException extends RuntimeException {
    public InvalidWordGivenException(String cause) {
        super(cause);
    }
}
