package hu.modeldriven.astah.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.astah.validator.core.ValidationExecution;

public class ValidationExecutedEvent implements Event {

    private final ValidationExecution validationExecution;

    public ValidationExecutedEvent(ValidationExecution validationExecution) {
        this.validationExecution = validationExecution;
    }

    public ValidationExecution validationExecution() {
        return validationExecution;
    }
}
