package hu.modeldriven.astah.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.astah.validator.core.ValidationSuite;

import java.util.List;

public class ValidationSuitesAvailableEvent implements Event {

    private final List<ValidationSuite> validationSuites;

    public ValidationSuitesAvailableEvent(List<ValidationSuite> validationSuites) {
        this.validationSuites = validationSuites;
    }

    public List<ValidationSuite> validationSuites() {
        return validationSuites;
    }
}
