package edu.hw6.task1.exception;

public class RemoveNonexistentKeyException extends RuntimeException {
    public RemoveNonexistentKeyException(String cause) {
        super(cause);
    }
}
