package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.core.ValidationRepository;
import hu.modeldriven.astah.validator.core.ValidationSuite;
import hu.modeldriven.astah.validator.core.impl.yaml.YAMLValidationRepository;
import hu.modeldriven.astah.validator.ui.event.ExceptionOccurredEvent;
import hu.modeldriven.astah.validator.ui.event.ValidationSuitesAvailableEvent;
import hu.modeldriven.astah.validator.ui.event.YAMLRepositoryFileSelectedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.core.usecase.UseCase;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class LoadYAMLRepositoryUseCase implements UseCase, EventHandler<YAMLRepositoryFileSelectedEvent> {

    private final EventBus eventBus;

    public LoadYAMLRepositoryUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handleEvent(YAMLRepositoryFileSelectedEvent yamlRepositoryFileSelectedEvent) {
        File file = yamlRepositoryFileSelectedEvent.file();
        ValidationRepository repository = new YAMLValidationRepository(file);

        try {
            List<ValidationSuite> suites = repository.validationSuites();
            eventBus.publish(new ValidationSuitesAvailableEvent(suites));
        } catch (Exception ex) {
            eventBus.publish(new ExceptionOccurredEvent(ex));
        }
    }


    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(YAMLRepositoryFileSelectedEvent.class);
    }

}
