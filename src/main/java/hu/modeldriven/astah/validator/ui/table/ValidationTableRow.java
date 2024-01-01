package hu.modeldriven.astah.validator.ui.table;

import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ValidationRule.Severity;

public class ValidationTableRow {

    private final ModelElement modelElement;
    private final String elementName;
    private final String ruleName;
    private final String message;
    private final Severity severity;

    public ValidationTableRow(ModelElement modelElement,
                              String elementName,
                              String ruleName,
                              String message,
                              Severity severity) {
        this.modelElement = modelElement;
        this.elementName = elementName;
        this.ruleName = ruleName;
        this.message = message;
        this.severity = severity;
    }

    public ModelElement getModelElement() {
        return modelElement;
    }

    public String getElementName() {
        return elementName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }
}

