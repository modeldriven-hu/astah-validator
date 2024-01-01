package hu.modeldriven.astah.validator.ui.table;

import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ValidationExecution;
import hu.modeldriven.astah.validator.core.ValidationRuleViolation;

import java.util.ArrayList;
import java.util.List;

public class ValidationTable {

    final List<hu.modeldriven.astah.validator.ui.table.ValidationTableRow> rows = new ArrayList<>();

    public ValidationTable(ValidationExecution validationExecution) {
        for (ValidationRuleViolation violation : validationExecution.violations()) {
            for (ModelElement element : violation.affectedElements()) {
                rows.add(
                        new hu.modeldriven.astah.validator.ui.table.ValidationTableRow(
                                element,
                                element.name(),
                                violation.rule().name(),
                                violation.rule().message(),
                                violation.rule().severity()
                        ));
            }
        }
    }

    public List<ValidationTableRow> rows() {
        return this.rows;
    }

}
