package hu.modeldriven.astah.validator.ui.event;

import hu.modeldriven.astah.validator.core.ModelPackage;
import hu.modeldriven.core.eventbus.Event;

public class PackageSelectedEvent implements Event {

    private final ModelPackage selectedPackage;

    public PackageSelectedEvent(ModelPackage selectedPackage) {
        this.selectedPackage = selectedPackage;
    }

    public ModelPackage selectedPackage() {
        return selectedPackage;
    }
}
