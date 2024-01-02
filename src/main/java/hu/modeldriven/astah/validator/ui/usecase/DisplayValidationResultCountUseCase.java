package hu.modeldriven.astah.validator.ui.usecase;

import hu.modeldriven.astah.validator.core.ValidationRuleViolation;
import hu.modeldriven.astah.validator.ui.event.ValidationExecutedEvent;
import hu.modeldriven.core.eventbus.Event;
import hu.modeldriven.core.eventbus.EventHandler;

import javax.swing.JLabel;
import java.util.Collections;
import java.util.List;

public class DisplayValidationResultCountUseCase implements EventHandler<ValidationExecutedEvent> {

    private final JLabel validationResultLabel;

    public DisplayValidationResultCountUseCase(JLabel validationResultLabel) {
        this.validationResultLabel = validationResultLabel;
    }

    @Override
    public void handleEvent(ValidationExecutedEvent event) {
        if (event.validationExecution().hasViolations()) {

            int ruleCount = 0;
            int affectedElementsCount = 0;
            int warningCount = 0;
            int errorCount = 0;
            int infoCount = 0;

            for (ValidationRuleViolation violation : event.validationExecution().violations()) {
                ruleCount++;
                affectedElementsCount += violation.affectedElements().size();

                switch (violation.rule().severity()) {

                    case WARNING:
                        warningCount += violation.affectedElements().size();
                        break;

                    case ERROR:
                        errorCount += violation.affectedElements().size();
                        break;

                    case INFO:
                        infoCount += violation.affectedElements().size();
                        break;

                }
            }

            validationResultLabel.setText(ruleCount + " rules violated for " + affectedElementsCount + " elements ("
                    + errorCount + " error, " + warningCount + " warnings, " + infoCount + " info)");
        } else {
            validationResultLabel.setText("No violations");
        }

    }

    @Override
    public List<Class<? extends Event>> subscribedEvents() {
        return Collections.singletonList(ValidationExecutedEvent.class);
    }
}
