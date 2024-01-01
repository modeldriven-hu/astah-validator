package hu.modeldriven.validator.core.impl;

import hu.modeldriven.validator.core.*;
import hu.modeldriven.validator.core.ValidationRule.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesValidatorImpl implements RulesValidator {

    @Override
    public ValidationExecution execute(ModelPackage pkg, ValidationSuite suite, ValidationRuleFilter... filters) {
        Map<ValidationRule, List<ModelElement>> failedRules = new HashMap<>();
        traverseModel(pkg, suite, failedRules);
        return new ValidationExecutionImpl(failedRules);
    }

    private void traverseModel(ModelPackage pkg, ValidationSuite suite, Map<ValidationRule, List<ModelElement>> failedRules) {
        for (ModelElement element : pkg.children()) {
            if (element instanceof ModelPackage) {
                traverseModel((ModelPackage) element, suite, failedRules);
            } else {
                validateModelElement(element, suite, failedRules);
            }
        }
    }

    private void validateModelElement(ModelElement modelElement, ValidationSuite suite, Map<ValidationRule, List<ModelElement>> failedRules) {
        for (ValidationRule rule : suite.validatorRules()) {

            try {
                ValidationResult result = rule.validate(modelElement);

                if (result == ValidationResult.INVALID) {
                    if (!failedRules.containsKey(rule)) {
                        failedRules.put(rule, new ArrayList<>());
                    }
                    failedRules.get(rule).add(modelElement);
                }

            } catch (ValidationFailedException ex) {
                // do nothing, maybe log
            }
        }
    }

}
