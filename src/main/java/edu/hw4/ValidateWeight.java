package edu.hw4;

import edu.hw4.exception.InvalidWeightException;
import java.util.Objects;

@SuppressWarnings("LocalVariableName")
public class ValidateWeight implements ValidationError {

    @Override
    public void check(Animal animal) {
        if (animal.weight() <= 0) {
            throw new InvalidWeightException("weight must be greater than 0");
        }
    }

    @Override
    public boolean isValid(Animal animal) {
        try {
            check(animal);
        } catch (InvalidWeightException exception) {
            return true;
        }
        return false;
    }

    @Override
    public String getValidationField() {
        return "Weight";
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
        String VALIDATE_WEIGHT = "VALIDATE_WEIGHT";
        return Objects.hash(VALIDATE_WEIGHT);
    }
}
