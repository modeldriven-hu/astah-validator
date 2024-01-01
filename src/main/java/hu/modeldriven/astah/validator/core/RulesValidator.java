package hu.modeldriven.astah.validator.core;

public interface RulesValidator {
    ValidationExecution execute(ModelPackage pkg, ValidationSuite suite, ValidationRuleFilter... filters);

}
