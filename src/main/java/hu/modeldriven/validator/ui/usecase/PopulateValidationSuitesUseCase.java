package hu.modeldriven.validator.ui.usecase;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.core.usecase.UseCase;
import hu.modeldriven.validator.core.ValidationSuite;
import hu.modeldriven.validator.ui.event.ValidationSuitesAvailableEvent;

import javax.swing.JComboBox;
import java.util.Arrays;
import java.util.List;

public class PopulateValidationSuitesUseCase implements UseCase, EventHandler<ValidationSuitesAvailableEvent> {

    private final EventBus eventBus;
    private final JComboBox<ValidationSuite> comboBox;

    public PopulateValidationSuitesUseCase(EventBus eventBus, JComboBox<ValidationSuite> comboBox) {
        this.eventBus = eventBus;
        this.comboBox = comboBox;
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ValidationSuitesAvailableEvent.class);
    }

    public void handleEvent(ValidationSuitesAvailableEvent validationSuitesAvailableEvent) {
        comboBox.removeAllItems();
        comboBox.setSelectedIndex(-1);

        for (ValidationSuite validationSuite : validationSuitesAvailableEvent.validationSuites()) {
            comboBox.addItem(validationSuite);
        }

    }
}
