package hu.modeldriven.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.validator.core.ValidationExecution;

public class ValidationExecutedEvent implements Event {

    private final ValidationExecution validationExecution;

    public ValidationExecutedEvent(ValidationExecution validationExecution) {
        this.validationExecution = validationExecution;
    }

    public ValidationExecution validationExecution() {
        return validationExecution;
    }
}
