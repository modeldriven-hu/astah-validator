package hu.modeldriven.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.validator.core.ValidationSuite;

public class ValidationSuiteSelectedEvent implements Event {

    private final ValidationSuite selectedSuite;

    public ValidationSuiteSelectedEvent(ValidationSuite selectedSuite) {
        this.selectedSuite = selectedSuite;
    }

    public ValidationSuite selectedSuite() {
        return selectedSuite;
    }
}
