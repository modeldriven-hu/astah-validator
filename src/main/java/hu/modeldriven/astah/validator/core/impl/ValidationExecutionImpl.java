package hu.modeldriven.astah.validator.core.impl;

import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ValidationExecution;
import hu.modeldriven.astah.validator.core.ValidationRule;
import hu.modeldriven.astah.validator.core.ValidationRuleViolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidationExecutionImpl implements ValidationExecution {

    private final Map<ValidationRule, List<ModelElement>> failedRules;

    public ValidationExecutionImpl(Map<ValidationRule, List<ModelElement>> failedRules) {
        this.failedRules = failedRules;
    }

    @Override
    public List<ValidationRuleViolation> violations() {

        List<ValidationRuleViolation> violations = new ArrayList<>();

        for (Map.Entry<ValidationRule, List<ModelElement>> entry : failedRules.entrySet()) {
            violations.add(new ValidationRuleViolationImpl(entry.getKey(), entry.getValue()));
        }

        return violations;
    }

    @Override
    public boolean hasViolations() {
        return failedRules.size() > 0;
    }
}
