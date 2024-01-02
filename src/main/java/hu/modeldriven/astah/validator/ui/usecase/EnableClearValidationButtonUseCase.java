package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.ui.event.ValidationSuitesAvailableEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JButton;
import java.util.Collections;
import java.util.List;

public class EnableClearValidationButtonUseCase implements EventHandler<ValidationSuitesAvailableEvent> {

    private final JButton clearValidationButton;

    public EnableClearValidationButtonUseCase(JButton clearValidationButton) {
        this.clearValidationButton = clearValidationButton;
    }

    @Override
    public void handleEvent(ValidationSuitesAvailableEvent e) {
        clearValidationButton.setEnabled(true);
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ValidationSuitesAvailableEvent.class);
    }
}
