package hu.modeldriven.astah.validator.core;

import java.util.List;

public interface ValidationSuite {

    String name();

    List<ValidationRule> validatorRules();

}
