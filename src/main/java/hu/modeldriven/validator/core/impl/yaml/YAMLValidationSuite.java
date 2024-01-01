package hu.modeldriven.validator.core.impl.yaml;

import hu.modeldriven.validator.core.ValidationRule;
import hu.modeldriven.validator.core.ValidationSuite;
import hu.modeldriven.validator.core.impl.script.ScriptExecutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YAMLValidationSuite implements ValidationSuite {

    private static final String RULES_KEY = "rules";

    private final Map<String, Object> data;
    private final ScriptExecutorRepository scriptExecutorRepository;

    public YAMLValidationSuite(Map<String, Object> data, ScriptExecutorRepository scriptRepository) {
        this.data = data;
        this.scriptExecutorRepository = scriptRepository;
    }

    @Override
    public String name() {
        return data.get("name").toString();
    }

    @Override
    public List<ValidationRule> validatorRules() {

        List<ValidationRule> result = new ArrayList<>();

        if (data.containsKey(RULES_KEY) && data.get(RULES_KEY) instanceof List) {
            for (Map<String, Object> rule : (List<Map<String, Object>>) data.get(RULES_KEY)) {
                result.add(new YAMLValidationRule(rule, scriptExecutorRepository));
            }
        }

        return result;
    }
}
