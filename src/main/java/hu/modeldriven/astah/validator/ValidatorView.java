package hu.modeldriven.astah.validator;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.project.ProjectAccessorFactory;
import com.change_vision.jude.api.inf.project.ProjectEvent;
import com.change_vision.jude.api.inf.project.ProjectEventListener;
import com.change_vision.jude.api.inf.ui.IPluginExtraTabView;
import com.change_vision.jude.api.inf.ui.ISelectionListener;
import hu.modeldriven.astah.validator.tool.AstahModelingTool;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.validator.core.ModelingToolRepresentation;
import hu.modeldriven.validator.ui.ValidationResultPanel;

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

        Component component = null;

        try {
            component = AstahAPI.getAstahAPI().getViewManager().getMainFrame();
        } catch (Exception e) {
            component = this;
        }

        EventBus eventBus = new EventBus();
        ModelingToolRepresentation toolRepresentation = new AstahModelingTool();
        ValidationResultPanel panel = new ValidationResultPanel(component, toolRepresentation, eventBus);
        return panel;
    }

    @Override
    public void projectChanged(ProjectEvent e) {
    }

    @Override
    public void projectClosed(ProjectEvent e) {
    }

    @Override
    public void projectOpened(ProjectEvent e) {
    }

    @Override
    public void addSelectionListener(ISelectionListener listener) {
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

    }

    public void deactivated() {

    }
}

