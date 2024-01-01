package hu.modeldriven.validator.ui;

import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.validator.core.ModelElement;
import hu.modeldriven.validator.core.ModelingToolRepresentation;
import hu.modeldriven.validator.core.ValidationRule;
import hu.modeldriven.validator.core.ValidationSuite;
import hu.modeldriven.validator.ui.combobox.ValidationSuiteComboBoxRenderer;
import hu.modeldriven.validator.ui.event.ClearValidationRequestedEvent;
import hu.modeldriven.validator.ui.event.SelectPackageRequestedEvent;
import hu.modeldriven.validator.ui.event.SelectRepositoryRequestedEvent;
import hu.modeldriven.validator.ui.event.ValidationRequestedEvent;
import hu.modeldriven.validator.ui.table.SeverityRenderer;
import hu.modeldriven.validator.ui.usecase.*;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class ValidationResultPanel extends AbstractValidationResultPanel {

    private final Component parentComponent;

    private final ModelingToolRepresentation modelingTool;
    private final EventBus eventBus;

    public ValidationResultPanel(Component parentComponent,  ModelingToolRepresentation modelingTool, EventBus eventBus) {
        super();
        this.parentComponent = parentComponent;
        this.modelingTool = modelingTool;
        this.eventBus = eventBus;
        initUIComponents();
        initUseCases();
    }

    private void initUIComponents() {
        this.validateButton.setEnabled(false);
        this.clearValidationButton.setEnabled(false);
        this.selectRepositoryButton.addActionListener(this::onSelectRepositoryPressed);
        this.selectPackageButton.addActionListener(this::onSelectPackagePressed);
        this.validateButton.addActionListener(this::onValidatePressed);
        this.clearValidationButton.addActionListener(this::onClearValidationPressed);
        this.suiteComboBox.setRenderer(new ValidationSuiteComboBoxRenderer());
        this.table.setDefaultRenderer(ValidationRule.Severity.class, new SeverityRenderer());
        createPopupMenu();
    }

    private void createPopupMenu() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem selectModelElementMenuItem = new JMenuItem("Select model element");

        selectModelElementMenuItem.addActionListener(actionEvent -> {
            if (table.getSelectedRow() != -1) {
                ModelElement modelElement = (ModelElement) table.getModel().getValueAt(table.getSelectedRow(), 0);
                modelingTool.selectModelElement(modelElement);
            }
        });

        JMenuItem selectOnDiagramMenuItem = new JMenuItem("Select on diagram");

        selectOnDiagramMenuItem.addActionListener(actionEvent -> {
            if (table.getSelectedRow() != -1) {
                ModelElement modelElement = (ModelElement)table.getModel().getValueAt(table.getSelectedRow(), 0);
                modelingTool.selectModelElementOnCurrentDiagram(modelElement);
            }
        });

        popup.add(selectModelElementMenuItem);
        popup.add(selectOnDiagramMenuItem);
        table.setComponentPopupMenu(popup);
    }

    private void initUseCases() {
        eventBus.subscribe(new DisplayExceptionUseCase(eventBus));
        eventBus.subscribe(new DisplayRepositorySelectorUseCase(eventBus));
        eventBus.subscribe(new LoadYAMLRepositoryUseCase(eventBus));
        eventBus.subscribe(new PopulateValidationSuitesUseCase(eventBus, suiteComboBox));
        eventBus.subscribe(new EnableValidateButtonUseCase(eventBus, validateButton));
        eventBus.subscribe(new EnableClearValidationButtonUseCase(eventBus, clearValidationButton));
        eventBus.subscribe(new ExecuteValidationUseCase(eventBus));
        eventBus.subscribe(new DisplayValidationExecutionUseCase(eventBus, table, cardPanel));
        eventBus.subscribe(new ClearValidationUseCase(eventBus, this.cardPanel));
        eventBus.subscribe(new DisplayPackageSelectorUseCase(parentComponent,
                modelingTool,
                eventBus));
    }

    private void onSelectRepositoryPressed(ActionEvent actionEvent) {
        eventBus.publish(new SelectRepositoryRequestedEvent());
    }

    private void onSelectPackagePressed(ActionEvent actionEvent) {
        eventBus.publish(new SelectPackageRequestedEvent());
    }

    private void onValidatePressed(ActionEvent actionEvent) {
        if (this.suiteComboBox.getSelectedItem() != null && modelingTool.selectedPackage().isPresent()) {
            ValidationSuite selectedSuite = this.suiteComboBox.getItemAt(this.suiteComboBox.getSelectedIndex());
            eventBus.publish(new ValidationRequestedEvent(modelingTool.selectedPackage().get(), selectedSuite));
        }
    }

    private void onClearValidationPressed(ActionEvent actionEvent) {
        eventBus.publish(new ClearValidationRequestedEvent());
    }

}
