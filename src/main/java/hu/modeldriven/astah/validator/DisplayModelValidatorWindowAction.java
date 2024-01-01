package hu.modeldriven.astah.validator;

import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;
import hu.modeldriven.astah.validator.tool.AstahModelingTool;
import hu.modeldriven.core.eventbus.EventBus;
import hu.modeldriven.validator.core.ModelingToolRepresentation;
import hu.modeldriven.validator.ui.ValidationResultPanel;

import javax.swing.JFrame;

public class DisplayModelValidatorWindowAction implements IPluginActionDelegate {
    @Override
    public Object run(IWindow iWindow) {

        EventBus eventBus = new EventBus();
        ModelingToolRepresentation modelingToolRepresentation = new AstahModelingTool();

        JFrame frame = new JFrame();
        frame.setContentPane(new ValidationResultPanel(iWindow.getParent(), modelingToolRepresentation, eventBus));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        return null;
    }
}
