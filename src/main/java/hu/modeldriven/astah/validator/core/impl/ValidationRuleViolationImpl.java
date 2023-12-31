package hu.modeldriven.astah.validator.core.impl;

import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ValidationRule;
import hu.modeldriven.astah.validator.core.ValidationRuleViolation;

import java.util.List;

public class ValidationRuleViolationImpl implements ValidationRuleViolation {

    private final ValidationRule rule;
    private final List<ModelElement> affectedElements;

    public ValidationRuleViolationImpl(ValidationRule rule, List<ModelElement> affectedElements) {
        this.rule = rule;
        this.affectedElements = affectedElements;
    }

    @Override
    public ValidationRule rule() {
        return rule;
    }

    @Override
    public List<ModelElement> affectedElements() {
        return affectedElements;
    }
}
