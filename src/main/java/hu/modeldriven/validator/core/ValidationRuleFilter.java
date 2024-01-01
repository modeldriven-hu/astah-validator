package hu.modeldriven.validator.core;

public interface ValidationRuleFilter {

    boolean appliesTo(ValidationRule rule);

    class All implements ValidationRuleFilter {

        @Override
        public boolean appliesTo(ValidationRule rule) {
            return true;
        }
    }

}
