package hu.modeldriven.validator.ui.usecase;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.validator.core.RulesValidator;
import hu.modeldriven.validator.core.ValidationExecution;
import hu.modeldriven.validator.core.impl.RulesValidatorImpl;
import hu.modeldriven.validator.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.validator.ui.event.ValidationExecutedEvent;
import hu.modeldriven.validator.ui.event.ValidationRequestedEvent;

import java.util.Arrays;
import java.util.List;

public class ExecuteValidationUseCase implements EventHandler<ValidationRequestedEvent> {

    private final EventBus eventBus;

    public ExecuteValidationUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(ValidationRequestedEvent e) {
        RulesValidator rulesValidator = new RulesValidatorImpl();

        try {
            ValidationExecution result = rulesValidator.execute(
                    e.selectedPackage(),
                    e.selectedSuite());

            eventBus.publish(new ValidationExecutedEvent(result));
        } catch (Exception ex) {
            eventBus.publish(new ExceptionOccurredEvent(ex));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ValidationRequestedEvent.class);
    }
}
