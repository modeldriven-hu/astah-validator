package hu.modeldriven.astah.validator.core;

import java.util.List;

public interface ValidationRuleViolation {

    ValidationRule rule();

    List<ModelElement> affectedElements();

}
