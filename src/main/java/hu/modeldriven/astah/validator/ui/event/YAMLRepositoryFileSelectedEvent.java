package hu.modeldriven.astah.validator.ui.event;

import hu.modeldriven.core.eventbus.Event;

import java.io.File;

public class YAMLRepositoryFileSelectedEvent implements Event {

    private final File file;

    public YAMLRepositoryFileSelectedEvent(File file) {
        this.file = file;
    }

    public File file() {
        return file;
    }
}
