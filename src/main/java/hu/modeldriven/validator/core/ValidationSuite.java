package hu.modeldriven.validator.core;

import java.util.List;

public interface ValidationSuite {

    String name();

    List<ValidationRule> validatorRules();

}
