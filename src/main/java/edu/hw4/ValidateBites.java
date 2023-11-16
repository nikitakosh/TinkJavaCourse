package edu.hw4;

import edu.hw4.exception.InvalidBitesException;
import java.util.Objects;

@SuppressWarnings("LocalVariableName")
public class ValidateBites implements ValidationError {


    @Override
    public void check(Animal animal) {
        if (animal.bites() == null) {
            throw new InvalidBitesException("invalid bites");
        }
    }

    @Override
    public boolean isValid(Animal animal) {
        try {
            check(animal);
        } catch (InvalidBitesException exception) {
            return true;
        }
        return false;
    }

    @Override
    public String getValidationField() {
        return "Bites";
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
        String VALIDATE_BITES = "VALIDATE_BITES";
        return Objects.hash(VALIDATE_BITES);
    }
}
