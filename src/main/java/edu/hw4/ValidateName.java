package edu.hw4;


import edu.hw4.exception.InvalidNameException;
import java.util.Objects;

@SuppressWarnings("LocalVariableName")
public class ValidateName implements ValidationError {


    @Override
    public void check(Animal animal) {
        if (animal.name().isEmpty()) {
            throw new InvalidNameException("name is empty");
        }
        for (int i = 0; i < animal.name().length(); i++) {
            if (Character.isDigit(animal.name().toCharArray()[i])) {
                throw new InvalidNameException("name contains digits");
            }
        }
    }

    @Override
    public boolean isValid(Animal animal) {
        try {
            check(animal);
        } catch (InvalidNameException exception) {
            return true;
        }
        return false;
    }

    @Override
    public String getValidationField() {
        return "Name";
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
        String VALIDATE_NAME = "VALIDATE_NAME";
        return Objects.hash(VALIDATE_NAME);
    }
}
