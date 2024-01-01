package hu.modeldriven.astah.validator.core;

import java.util.List;

public interface ValidationExecution {

    List<ValidationRuleViolation> violations();

    boolean hasViolations();

}
