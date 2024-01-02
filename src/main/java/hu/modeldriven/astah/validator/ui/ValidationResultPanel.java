package hu.modeldriven.astah.validator.ui;

import hu.modeldriven.astah.validator.core.ModelingToolException;
import hu.modeldriven.astah.validator.core.ModelingToolRepresentation;
import hu.modeldriven.astah.validator.core.ValidationRule;
import hu.modeldriven.astah.validator.ui.combobox.ValidationSuiteComboBoxRenderer;
import hu.modeldriven.astah.validator.ui.event.ClearValidationRequestedEvent;
import hu.modeldriven.astah.validator.ui.event.SelectPackageRequestedEvent;
import hu.modeldriven.astah.validator.ui.event.ValidationSuiteSelectedEvent;
import hu.modeldriven.astah.validator.ui.table.SeverityRenderer;
import hu.modeldriven.astah.validator.ui.usecase.ClearValidationUseCase;
import hu.modeldriven.astah.validator.ui.usecase.DisplayPackageSelectorUseCase;
import hu.modeldriven.astah.validator.ui.usecase.DisplayValidationResultCountUseCase;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class ValidationResultPanel extends AbstractValidationResultPanel {

    private final Component parentComponent;

    private final transient ModelingToolRepresentation modelingTool;
    private final transient EventBus eventBus;

    public ValidationResultPanel(Component parentComponent, hu.modeldriven.astah.validator.core.ModelingToolRepresentation modelingTool, EventBus eventBus) {
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
        this.suiteComboBox.addItemListener(this::onItemSelected);
        this.table.setDefaultRenderer(ValidationRule.Severity.class, new SeverityRenderer());
        createPopupMenu();
    }

    private void createPopupMenu() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem selectModelElementMenuItem = new JMenuItem("Select model element");

        selectModelElementMenuItem.addActionListener(actionEvent -> {
            if (table.getSelectedRow() != -1) {
                try {
                    hu.modeldriven.astah.validator.core.ModelElement modelElement = (hu.modeldriven.astah.validator.core.ModelElement) table.getModel().getValueAt(table.getSelectedRow(), 0);
                    modelingTool.selectModelElement(modelElement);
                } catch (ModelingToolException e) {
                    eventBus.publish(new hu.modeldriven.astah.validator.ui.event.ExceptionOccurredEvent(e));
                }
            }
        });

        JMenuItem selectOnDiagramMenuItem = new JMenuItem("Select on diagram");

        selectOnDiagramMenuItem.addActionListener(actionEvent -> {
            if (table.getSelectedRow() != -1) {
                try {
                    hu.modeldriven.astah.validator.core.ModelElement modelElement = (hu.modeldriven.astah.validator.core.ModelElement) table.getModel().getValueAt(table.getSelectedRow(), 0);
                    modelingTool.selectModelElementOnCurrentDiagram(modelElement);
                } catch (ModelingToolException e) {
                    eventBus.publish(new hu.modeldriven.astah.validator.ui.event.ExceptionOccurredEvent(e));
                }
            }
        });

        popup.add(selectModelElementMenuItem);
        popup.add(selectOnDiagramMenuItem);
        table.setComponentPopupMenu(popup);
    }

    private void initUseCases() {
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.DisplayExceptionUseCase());
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.DisplayRepositorySelectorUseCase(eventBus));
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.LoadYAMLRepositoryUseCase(eventBus));
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.PopulateValidationSuitesUseCase(suiteComboBox));
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.EnableValidateButtonUseCase(validateButton));
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.EnableClearValidationButtonUseCase(clearValidationButton));
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.ExecuteValidationUseCase(eventBus));
        eventBus.subscribe(new hu.modeldriven.astah.validator.ui.usecase.DisplayValidationExecutionUseCase(table, cardPanel));
        eventBus.subscribe(new ClearValidationUseCase(cardPanel));
        eventBus.subscribe(new DisplayPackageSelectorUseCase(parentComponent,
                modelingTool,
                eventBus));
        eventBus.subscribe(new DisplayValidationResultCountUseCase(validationResultLabel));
    }

    private void onSelectRepositoryPressed(ActionEvent actionEvent) {
        eventBus.publish(new hu.modeldriven.astah.validator.ui.event.SelectRepositoryRequestedEvent());
    }

    private void onSelectPackagePressed(ActionEvent actionEvent) {
        eventBus.publish(new SelectPackageRequestedEvent());
    }


    private void onItemSelected(ItemEvent itemEvent) {

        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
            hu.modeldriven.astah.validator.core.ValidationSuite selectedSuite = (hu.modeldriven.astah.validator.core.ValidationSuite) itemEvent.getItem();
            eventBus.publish(new ValidationSuiteSelectedEvent(selectedSuite));
        }

    }

    private void onValidatePressed(ActionEvent actionEvent) {
        eventBus.publish(new hu.modeldriven.astah.validator.ui.event.ValidationRequestedEvent());
    }

    private void onClearValidationPressed(ActionEvent actionEvent) {
        eventBus.publish(new ClearValidationRequestedEvent());
    }

}
