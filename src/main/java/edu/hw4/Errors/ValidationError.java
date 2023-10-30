package edu.hw4.Errors;

import edu.hw4.Animal;
import java.util.HashSet;
import java.util.Set;

public class ValidationError extends Throwable {

    private static final int MAX_LENGTH_0F_NAME = 4;
    private static final int MAX_AGE = 200;
    private static final int MAX_HEIGHT = 500;
    private static final int MAX_WEIGHT = 100;

    public ValidationError(String message) {
        super(message);
    }

    public static Set<ValidationError> getListOfValidationError(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.name().split(" ").length >= MAX_LENGTH_0F_NAME) {
            errors.add(new NameError("Incorrect name"));
        }

        if (animal.age() < 1 || animal.age() >= MAX_AGE) {
            errors.add(new AgeError("Incorrect age"));
        }

        if (animal.height() <= 0 || animal.height() >= MAX_HEIGHT) {
            errors.add(new HeightError("Incorrect height"));
        }

        if (animal.weight() < 1 || animal.weight() >= MAX_WEIGHT) {
            errors.add(new WeightError("Incorrect weight"));
        }

        return errors;
    }
}
