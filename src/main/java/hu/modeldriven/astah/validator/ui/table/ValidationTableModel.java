package hu.modeldriven.astah.validator.ui.table;

import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ValidationRule;

import javax.swing.table.AbstractTableModel;

public class ValidationTableModel extends AbstractTableModel {

    private final ValidationTable validationTable;

    public ValidationTableModel(ValidationTable validationTable) {
        super();
        this.validationTable = validationTable;
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Model Element";
            case 1:
                return "Element";
            case 2:
                return "Severity";
            case 3:
                return "Rule";
            case 4:
                return "Error";
        }

        return "";
    }

    @Override
    public int getRowCount() {
        return this.validationTable.rows().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int column) {

        switch (column) {
            case 0:
                return ModelElement.class;
            case 1:
            case 3:
            case 4:
                return String.class;
            case 2:
                return ValidationRule.Severity.class;
        }

        return String.class;
    }

    @Override
    public Object getValueAt(int row, int column) {

        ValidationTableRow validationTableRow = this.validationTable.rows().get(row);

        switch (column) {
            case 0:
                return validationTableRow.getModelElement();
            case 1:
                return validationTableRow.getElementName();
            case 2:
                return validationTableRow.getSeverity();
            case 3:
                return validationTableRow.getRuleName();
            case 4:
                return validationTableRow.getMessage();
        }

        return "";
    }

}
