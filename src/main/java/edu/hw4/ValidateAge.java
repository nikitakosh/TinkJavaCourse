package edu.hw4;

import edu.hw4.exception.InvalidAgeException;
import java.util.Objects;

@SuppressWarnings("LocalVariableName")
public class ValidateAge implements ValidationError {


    @Override
    public void check(Animal animal) {
        if (animal.age() <= 0) {
            throw new InvalidAgeException("age must be greater than 0");
        }
    }

    @Override
    public boolean isValid(Animal animal) {
        try {
            check(animal);
        } catch (InvalidAgeException exception) {
            return true;
        }
        return false;
    }

    @Override
    public String getValidationField() {
        return "Age";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        String VALIDATE_AGE = "VALIDATE_AGE";
        return Objects.hash(VALIDATE_AGE);
    }
}
