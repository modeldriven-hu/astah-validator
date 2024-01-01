package hu.modeldriven.validator.ui.table;

import hu.modeldriven.validator.core.ModelElement;
import hu.modeldriven.validator.core.ValidationExecution;
import hu.modeldriven.validator.core.ValidationRuleViolation;

import java.util.ArrayList;
import java.util.List;

public class ValidationTable {

    final List<ValidationTableRow> rows = new ArrayList<>();

    public ValidationTable(ValidationExecution validationExecution) {
        for (ValidationRuleViolation violation : validationExecution.violations()) {
            for (ModelElement element : violation.affectedElements()) {
                rows.add(
                        new ValidationTableRow(
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
