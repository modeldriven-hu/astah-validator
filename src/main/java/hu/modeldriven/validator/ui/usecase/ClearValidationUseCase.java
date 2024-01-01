package hu.modeldriven.validator.ui.usecase;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.validator.ui.event.ClearValidationRequestedEvent;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.Arrays;
import java.util.List;

public class ClearValidationUseCase implements EventHandler<ClearValidationRequestedEvent> {

    private final EventBus eventBus;
    private final JPanel cardPanel;

    public ClearValidationUseCase(EventBus eventBus, JPanel cardPanel) {
        this.eventBus = eventBus;
        this.cardPanel = cardPanel;
    }

    @Override
    public void handleEvent(ClearValidationRequestedEvent e) {
        if (cardPanel.getLayout() instanceof CardLayout) {
            CardLayout layout = (CardLayout) cardPanel.getLayout();
            layout.show(cardPanel, "infoPanel");
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ClearValidationRequestedEvent.class);
    }

}
