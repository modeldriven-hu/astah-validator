package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.ui.event.ValidationExecutedEvent;
import hu.modeldriven.astah.validator.ui.table.ValidationTable;
import hu.modeldriven.astah.validator.ui.table.ValidationTableModel;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.util.Collections;
import java.util.List;

public class DisplayValidationExecutionUseCase implements EventHandler<ValidationExecutedEvent> {

    private final JTable validationTable;

    private final JPanel cardPanel;

    public DisplayValidationExecutionUseCase(JTable validationTable, JPanel cardPanel) {
        this.validationTable = validationTable;
        this.cardPanel = cardPanel;
    }

    @Override
    public void handleEvent(ValidationExecutedEvent e) {
        validationTable.setModel(new ValidationTableModel(
                new ValidationTable(
                        e.validationExecution()
                ))
        );

        validationTable.removeColumn(validationTable.getColumnModel().getColumn(0));

        if (cardPanel.getLayout() instanceof CardLayout) {
            CardLayout layout = (CardLayout) cardPanel.getLayout();
            layout.show(cardPanel, "tablePanel");
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ValidationExecutedEvent.class);
    }
}
