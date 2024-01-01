package hu.modeldriven.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.validator.core.ModelPackage;
import hu.modeldriven.validator.core.ValidationSuite;

public class ValidationRequestedEvent implements Event {

    private final ModelPackage selectedPackage;
    private final ValidationSuite selectedSuite;

    public ValidationRequestedEvent(ModelPackage selectedPackage, ValidationSuite selectedSuite) {
        this.selectedPackage = selectedPackage;
        this.selectedSuite = selectedSuite;
    }

    public ModelPackage selectedPackage() {
        return selectedPackage;
    }

    public ValidationSuite selectedSuite() {
        return selectedSuite;
    }
}
