package hu.modeldriven.astah.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.astah.validator.core.ModelPackage;

public class PackageSelectedEvent implements Event {

    private final ModelPackage selectedPackage;

    public PackageSelectedEvent(ModelPackage selectedPackage){
        this.selectedPackage = selectedPackage;
    }

    public ModelPackage selectedPackage() {
        return selectedPackage;
    }
}
