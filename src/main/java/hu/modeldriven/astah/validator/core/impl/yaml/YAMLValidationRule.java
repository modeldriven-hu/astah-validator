package hu.modeldriven.astah.validator.core.impl.yaml;

import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ValidationFailedException;
import hu.modeldriven.astah.validator.core.ValidationRule;
import hu.modeldriven.astah.validator.core.impl.script.ScriptExecutor;
import hu.modeldriven.astah.validator.core.impl.script.ScriptExecutorRepository;

import java.util.Map;

public class YAMLValidationRule implements ValidationRule {

    private final Map<String, Object> data;
    private final hu.modeldriven.astah.validator.core.impl.script.ScriptExecutorRepository scriptExecutorRepository;

    public YAMLValidationRule(Map<String, Object> data, ScriptExecutorRepository scriptExecutorRepository) {
        this.data = data;
        this.scriptExecutorRepository = scriptExecutorRepository;
    }

    @Override
    public String name() {
        return this.data.getOrDefault("name", "").toString();
    }

    @Override
    public String message() {
        return this.data.getOrDefault("message", "").toString();
    }

    @Override
    public Severity severity() {
        return Severity.valueOf(this.data.getOrDefault("severity", "").toString().toUpperCase());
    }

    @Override
    public ValidationResult validate(ModelElement element) throws ValidationFailedException {

        try {
            String language = this.data.getOrDefault("language", "").toString();
            String script = this.data.getOrDefault("script", "").toString();

            ScriptExecutor scriptExecutor = scriptExecutorRepository.getScriptExecutor(language);
            Object result = scriptExecutor.execute(script, element.value());

            if (result instanceof Boolean) {
                if (Boolean.TRUE.equals(result)) {
                    return ValidationResult.VALID;
                } else {
                    return ValidationResult.INVALID;
                }
            } else {
                throw new ValidationFailedException("Invalid result type, expected Boolean");
            }

        } catch (Exception ex) {
            throw new ValidationFailedException(ex);
        }
    }
}
