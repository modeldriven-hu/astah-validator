package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.ui.event.SelectRepositoryRequestedEvent;
import hu.modeldriven.astah.validator.ui.event.YAMLRepositoryFileSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.core.usecase.UseCase;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class DisplayRepositorySelectorUseCase implements UseCase, EventHandler<SelectRepositoryRequestedEvent> {

    private final EventBus eventBus;

    public DisplayRepositorySelectorUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(SelectRepositoryRequestedEvent.class);
    }

    @Override
    public void handleEvent(SelectRepositoryRequestedEvent selectRepositoryRequestedEvent) {
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("YAML", "yaml", "yml");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            eventBus.publish(new YAMLRepositoryFileSelectedEvent(f));
        }
    }

}
