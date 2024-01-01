package hu.modeldriven.validator.ui.usecase;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.validator.ui.event.PackageSelectedEvent;
import hu.modeldriven.validator.ui.event.ValidationSuitesAvailableEvent;

import javax.swing.JButton;
import java.util.Arrays;
import java.util.List;

public class EnableValidateButtonUseCase implements EventHandler<Event> {

    private final EventBus eventBus;
    private final JButton validateButton;

    private final SelectionData selectionData;

    public EnableValidateButtonUseCase(EventBus eventBus, JButton validateButton) {
        this.eventBus = eventBus;
        this.validateButton = validateButton;
        this.selectionData = new SelectionData();
    }

    @Override
    public void handleEvent(Event e) {

        if (e instanceof ValidationSuitesAvailableEvent){
            this.selectionData.suitesAvailable = true;
        }

        if (e instanceof PackageSelectedEvent){
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

    class SelectionData {

        boolean suitesAvailable;
        boolean packageSelected;

    }

}
