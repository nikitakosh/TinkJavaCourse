package edu.hw4;

public interface ValidationError {
    void check(Animal animal);

    boolean isValid(Animal animal);

    String getValidationField();
}
