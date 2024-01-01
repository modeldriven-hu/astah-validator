package hu.modeldriven.validator.ui.event;

import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.validator.core.ModelPackage;

public class PackageSelectedEvent implements Event {

    private final ModelPackage selectedPackage;

    public PackageSelectedEvent(ModelPackage selectedPackage){
        this.selectedPackage = selectedPackage;
    }

    public ModelPackage selectedPackage() {
        return selectedPackage;
    }
}
