package hu.modeldriven.validator.core;

public class ValidationFailedException extends Exception {

    public ValidationFailedException(String message) {
        super(message);
    }

    public ValidationFailedException(Exception e) {
        super(e);
    }

}
