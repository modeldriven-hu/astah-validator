package hu.modeldriven.astah.validator.ui.event;

import hu.modeldriven.astah.validator.core.ValidationSuite;
import hu.modeldriven.core.eventbus.Event;

public class ValidationSuiteSelectedEvent implements Event {

    private final ValidationSuite selectedSuite;

    public ValidationSuiteSelectedEvent(ValidationSuite selectedSuite) {
        this.selectedSuite = selectedSuite;
    }

    public ValidationSuite selectedSuite() {
        return selectedSuite;
    }
}
