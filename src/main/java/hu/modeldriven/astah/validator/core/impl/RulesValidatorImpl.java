package hu.modeldriven.astah.validator.core.impl;

import hu.modeldriven.astah.validator.core.ValidationExecution;
import hu.modeldriven.astah.validator.core.ValidationRule.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesValidatorImpl implements hu.modeldriven.astah.validator.core.RulesValidator {

    @Override
    public ValidationExecution execute(hu.modeldriven.astah.validator.core.ModelPackage pkg, hu.modeldriven.astah.validator.core.ValidationSuite suite, hu.modeldriven.astah.validator.core.ValidationRuleFilter... filters) {
        Map<hu.modeldriven.astah.validator.core.ValidationRule, List<hu.modeldriven.astah.validator.core.ModelElement>> failedRules = new HashMap<>();
        traverseModel(pkg, suite, failedRules);
        return new ValidationExecutionImpl(failedRules);
    }

    private void traverseModel(hu.modeldriven.astah.validator.core.ModelPackage pkg, hu.modeldriven.astah.validator.core.ValidationSuite suite, Map<hu.modeldriven.astah.validator.core.ValidationRule, List<hu.modeldriven.astah.validator.core.ModelElement>> failedRules) {
        for (hu.modeldriven.astah.validator.core.ModelElement element : pkg.children()) {
            if (element instanceof hu.modeldriven.astah.validator.core.ModelPackage) {
                traverseModel((hu.modeldriven.astah.validator.core.ModelPackage) element, suite, failedRules);
            } else {
                validateModelElement(element, suite, failedRules);
            }
        }
    }

    private void validateModelElement(hu.modeldriven.astah.validator.core.ModelElement modelElement, hu.modeldriven.astah.validator.core.ValidationSuite suite, Map<hu.modeldriven.astah.validator.core.ValidationRule, List<hu.modeldriven.astah.validator.core.ModelElement>> failedRules) {
        for (hu.modeldriven.astah.validator.core.ValidationRule rule : suite.validatorRules()) {

            try {
                ValidationResult result = rule.validate(modelElement);

                if (result == ValidationResult.INVALID) {
                    if (!failedRules.containsKey(rule)) {
                        failedRules.put(rule, new ArrayList<>());
                    }
                    failedRules.get(rule).add(modelElement);
                }

            } catch (hu.modeldriven.astah.validator.core.ValidationFailedException ex) {
                // do nothing, maybe log
            }
        }
    }

}
