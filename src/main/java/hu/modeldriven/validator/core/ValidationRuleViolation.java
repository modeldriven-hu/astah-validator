package hu.modeldriven.validator.core;

import java.util.List;

public interface ValidationRuleViolation {

    ValidationRule rule();

    List<ModelElement> affectedElements();

}
