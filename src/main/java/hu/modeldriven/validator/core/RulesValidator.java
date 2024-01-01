package hu.modeldriven.validator.core;

public interface RulesValidator {
    ValidationExecution execute(ModelPackage pkg, ValidationSuite suite, ValidationRuleFilter... filters);

}
