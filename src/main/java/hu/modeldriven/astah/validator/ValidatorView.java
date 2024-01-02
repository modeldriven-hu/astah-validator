package hu.modeldriven.astah.validator;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;
import hu.modeldriven.astah.validator.core.ModelingToolRepresentation;
import hu.modeldriven.astah.validator.tool.AstahModelingTool;
import hu.modeldriven.astah.validator.ui.ValidationResultPanel;
import hu.modeldriven.core.eventbus.EventBus;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

public class ValidatorView extends JPanel implements IPluginExtraTabView, ProjectEventListener {

    public ValidatorView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        add(createContentPane(), BorderLayout.CENTER);
        addProjectEventListener();
    }

    private void addProjectEventListener() {
        try {
            ProjectAccessor projectAccessor = ProjectAccessorFactory.getProjectAccessor();
            projectAccessor.addProjectEventListener(this);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }

    private Container createContentPane() {

        Component component;

        try {
            component = AstahAPI.getAstahAPI().getViewManager().getMainFrame();
        } catch (Exception e) {
            component = this;
        }

        EventBus eventBus = new EventBus();
        ModelingToolRepresentation toolRepresentation = new AstahModelingTool();
        return new ValidationResultPanel(component, toolRepresentation, eventBus);
    }

    @Override
    public void projectChanged(ProjectEvent e) {
        // nothing to do here
    }

    @Override
    public void projectClosed(ProjectEvent e) {
        // nothing to do here
    }

    @Override
    public void projectOpened(ProjectEvent e) {
        // nothing to do here
    }

    @Override
    public void addSelectionListener(ISelectionListener listener) {
        // nothing to do here
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public String getDescription() {
        return "Validator View";
    }

    @Override
    public String getTitle() {
        return "Model Validation";
    }

    public void activated() {
        // nothing to do here
    }

    public void deactivated() {
        // nothing to do here
    }
}

