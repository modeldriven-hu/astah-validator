package hu.modeldriven.validator.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.validator.core.ModelPackage;
import hu.modeldriven.validator.core.RulesValidator;
import hu.modeldriven.validator.core.ValidationExecution;
import hu.modeldriven.validator.core.ValidationSuite;
import hu.modeldriven.validator.core.impl.RulesValidatorImpl;
import hu.modeldriven.validator.ui.event.*;

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

        System.err.println("Handling execute validation use case");

        if (event instanceof PackageSelectedEvent) {
            this.selectionData.selectedPackage = ((PackageSelectedEvent)event).selectedPackage();
        }

        if (event instanceof ValidationSuiteSelectedEvent) {
            this.selectionData.selectedSuite = ((ValidationSuiteSelectedEvent)event).selectedSuite();
        }

        if (event instanceof ValidationRequestedEvent) {
            RulesValidator rulesValidator = new RulesValidatorImpl();

            try {
                ValidationExecution result = rulesValidator.execute(
                        selectionData.selectedPackage,
                        selectionData.selectedSuite);

                eventBus.publish(new ValidationExecutedEvent(result));
            } catch (Exception ex) {
                eventBus.publish(new ExceptionOccurredEvent(ex));
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

    class SelectionData {
        ModelPackage selectedPackage;
        ValidationSuite selectedSuite;
    }

}
