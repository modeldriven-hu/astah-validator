package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.ui.event.ClearValidationRequestedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.Collections;
import java.util.List;

public class ClearValidationUseCase implements EventHandler<ClearValidationRequestedEvent> {

    private final JPanel cardPanel;

    public ClearValidationUseCase(JPanel cardPanel) {
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
        return Collections.singletonList(ClearValidationRequestedEvent.class);
    }

}
