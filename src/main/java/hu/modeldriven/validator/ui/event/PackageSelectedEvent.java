package hu.modeldriven.validator.ui.event;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.core.eventbus.Event;

public class PackageSelectedEvent implements Event {

    private final IPackage selectedPackage;

    public PackageSelectedEvent(IPackage selectedPackage){
        this.selectedPackage = selectedPackage;
    }

    public IPackage selectedPackage() {
        return selectedPackage;
    }
}
