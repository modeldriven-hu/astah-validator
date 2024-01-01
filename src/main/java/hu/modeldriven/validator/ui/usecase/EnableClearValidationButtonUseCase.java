package hu.modeldriven.validator.ui.usecase;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.validator.ui.event.ValidationSuitesAvailableEvent;

import javax.swing.JButton;
import java.util.Arrays;
import java.util.List;

public class EnableClearValidationButtonUseCase implements EventHandler<ValidationSuitesAvailableEvent> {

    private final EventBus eventBus;
    private final JButton clearValidationButton;

    public EnableClearValidationButtonUseCase(EventBus eventBus, JButton clearValidationButton) {
        this.eventBus = eventBus;
        this.clearValidationButton = clearValidationButton;
    }

    @Override
    public void handleEvent(ValidationSuitesAvailableEvent e) {
        clearValidationButton.setEnabled(true);
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ValidationSuitesAvailableEvent.class);
    }
}
