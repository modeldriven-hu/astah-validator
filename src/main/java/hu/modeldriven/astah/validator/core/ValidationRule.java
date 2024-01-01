package hu.modeldriven.astah.validator.core;

public interface ValidationRule {

    enum ValidationResult {
        VALID, INVALID
    }

    enum Severity {
        INFO, WARNING, ERROR
    }

    String name();

    String message();

    Severity severity();

    ValidationResult validate(ModelElement element) throws ValidationFailedException;

}
