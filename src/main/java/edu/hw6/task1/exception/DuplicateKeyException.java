package edu.hw6.task1.exception;

public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException(String cause) {
        super(cause);
    }
}
