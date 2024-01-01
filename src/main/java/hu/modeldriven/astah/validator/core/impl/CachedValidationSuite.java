package hu.modeldriven.astah.validator.core.impl;

import hu.modeldriven.astah.validator.core.ValidationRule;
import hu.modeldriven.astah.validator.core.ValidationSuite;

import java.util.ArrayList;
import java.util.List;

public class CachedValidationSuite implements ValidationSuite {

    private final ValidationSuite delegate;
    private final List<ValidationRule> cachedRules;

    public CachedValidationSuite(ValidationSuite delegate) {
        this.delegate = delegate;
        this.cachedRules = new ArrayList<ValidationRule>();
    }

    @Override
    public String name() {
        return delegate.name();
    }

    @Override
    public List<ValidationRule> validatorRules() {
        if (cachedRules.isEmpty()) {
            cachedRules.addAll(delegate.validatorRules());
        }
        return cachedRules;
    }
}
