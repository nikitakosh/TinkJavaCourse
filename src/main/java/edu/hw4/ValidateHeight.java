package edu.hw4;

import edu.hw4.exception.InvalidHeightException;
import java.util.Objects;

@SuppressWarnings("LocalVariableName")
public class ValidateHeight implements ValidationError {


    @Override
    public void check(Animal animal) {
        if (animal.height() <= 0) {
            throw new InvalidHeightException("height must be greater than 0");
        }
    }

    @Override
    public boolean isValid(Animal animal) {
        try {
            check(animal);
        } catch (InvalidHeightException exception) {
            return true;
        }
        return false;
    }

    @Override
    public String getValidationField() {
        return "Height";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        String VALIDATE_HEIGHT = "VALIDATE_HEIGHT";
        return Objects.hash(VALIDATE_HEIGHT);
    }
}
