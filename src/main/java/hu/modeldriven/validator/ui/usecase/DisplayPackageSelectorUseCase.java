package hu.modeldriven.validator.ui.usecase;

import com.change_vision.jude.api.inf.model.IPackage;
import groovyjarjarpicocli.CommandLine;
import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;
import hu.modeldriven.astah.validator.tool.AstahModelPackage;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.validator.core.ModelingToolException;
import hu.modeldriven.validator.core.ModelingToolRepresentation;
import hu.modeldriven.validator.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.validator.ui.event.PackageSelectedEvent;
import hu.modeldriven.validator.ui.event.SelectPackageRequestedEvent;

import java.awt.Component;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DisplayPackageSelectorUseCase implements EventHandler<SelectPackageRequestedEvent>, Consumer<IPackage> {

    private final Component parentComponent;

    private final ModelingToolRepresentation toolRepresentation;
    private final EventBus eventBus;

    public DisplayPackageSelectorUseCase(Component parentComponent, ModelingToolRepresentation toolRepresentation, EventBus eventBus){
        this.parentComponent = parentComponent;
        this.toolRepresentation = toolRepresentation;
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(SelectPackageRequestedEvent event) {

        // The packageSelectorDialog is breaking encapsulation because it is returning
        // a model tool specific element instead of a generic one

        // this needs to be fixed before moving all the validator code into a separate
        // package for code reuse

        try {

            PackageSelectorDialog dialog = new PackageSelectorDialog(
                    parentComponent,
                    (IPackage) toolRepresentation.rootPackage().value(),
                    this);
            dialog.show();

        } catch (ModelingToolException e){
            eventBus.publish(new ExceptionOccurredEvent(e));
        }
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(SelectPackageRequestedEvent.class);
    }

    @Override
    public void accept(IPackage iPackage) {
        eventBus.publish(new PackageSelectedEvent(new AstahModelPackage(iPackage)));
    }
}
