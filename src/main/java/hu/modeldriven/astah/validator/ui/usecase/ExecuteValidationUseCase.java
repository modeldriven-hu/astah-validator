package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.ui.event.PackageSelectedEvent;
import hu.modeldriven.astah.validator.ui.event.ValidationRequestedEvent;
import hu.modeldriven.astah.validator.ui.event.ValidationSuiteSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.astah.validator.core.ModelPackage;
import hu.modeldriven.astah.validator.core.RulesValidator;
import hu.modeldriven.astah.validator.core.ValidationExecution;
import hu.modeldriven.astah.validator.core.ValidationSuite;
import hu.modeldriven.astah.validator.core.impl.RulesValidatorImpl;

import java.util.Arrays;
import java.util.List;

public class ExecuteValidationUseCase implements EventHandler<Event> {

    private final EventBus eventBus;
    private final SelectionData selectionData;

    public ExecuteValidationUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
        this.selectionData = new SelectionData();
    }

    @Override
    public void handleEvent(Event event) {

        if (event instanceof PackageSelectedEvent) {
            this.selectionData.selectedPackage = ((PackageSelectedEvent)event).selectedPackage();
        }

        if (event instanceof ValidationSuiteSelectedEvent) {
            this.selectionData.selectedSuite = ((ValidationSuiteSelectedEvent)event).selectedSuite();
        }

        if (event instanceof hu.modeldriven.astah.validator.ui.event.ValidationRequestedEvent) {
            RulesValidator rulesValidator = new RulesValidatorImpl();

            try {
                ValidationExecution result = rulesValidator.execute(
                        selectionData.selectedPackage,
                        selectionData.selectedSuite);

                eventBus.publish(new hu.modeldriven.astah.validator.ui.event.ValidationExecutedEvent(result));
            } catch (Exception ex) {
                eventBus.publish(new hu.modeldriven.astah.validator.ui.event.ExceptionOccurredEvent(ex));
            }
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {

        return Arrays.asList(
                ValidationRequestedEvent.class,
                PackageSelectedEvent.class,
                ValidationSuiteSelectedEvent.class);
    }

    static class SelectionData {
        ModelPackage selectedPackage;
        ValidationSuite selectedSuite;
    }

}
