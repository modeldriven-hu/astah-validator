package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.ui.event.PackageSelectedEvent;
import hu.modeldriven.astah.validator.ui.event.ValidationSuitesAvailableEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JButton;
import java.util.Arrays;
import java.util.List;

public class EnableValidateButtonUseCase implements EventHandler<Event> {

    private final JButton validateButton;

    private final SelectionData selectionData;

    public EnableValidateButtonUseCase(JButton validateButton) {
        this.validateButton = validateButton;
        this.selectionData = new SelectionData();
    }

    @Override
    public void handleEvent(Event e) {

        if (e instanceof ValidationSuitesAvailableEvent) {
            this.selectionData.suitesAvailable = true;
        }

        if (e instanceof PackageSelectedEvent) {
            this.selectionData.packageSelected = true;
        }

        if (selectionData.suitesAvailable && selectionData.packageSelected) {
            validateButton.setEnabled(true);
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ValidationSuitesAvailableEvent.class, PackageSelectedEvent.class);
    }

    static class SelectionData {

        boolean suitesAvailable;
        boolean packageSelected;

    }

}
