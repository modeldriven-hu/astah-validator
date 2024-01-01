package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.core.eventbus.EventHandler;
import hu.modeldriven.core.usecase.UseCase;
import hu.modeldriven.astah.validator.ui.event.ExceptionOccurredEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class DisplayExceptionUseCase implements UseCase, EventHandler<ExceptionOccurredEvent> {

    private final EventBus eventBus;

    public DisplayExceptionUseCase(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Arrays.asList(ExceptionOccurredEvent.class);
    }

    @Override
    public void handleEvent(ExceptionOccurredEvent exceptionOccurredEvent) {

        StringWriter stringWriter = new StringWriter();
        exceptionOccurredEvent.exception().printStackTrace(new PrintWriter(stringWriter));

        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setText(stringWriter.toString());
        text.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "Exception occurred!", JOptionPane.ERROR_MESSAGE);
    }
}
