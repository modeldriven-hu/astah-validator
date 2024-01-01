package hu.modeldriven.astah.validator.tool;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IEntity;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.view.IProjectViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;
import hu.modeldriven.validator.core.ModelElement;
import hu.modeldriven.validator.core.ModelPackage;
import hu.modeldriven.validator.core.ModelingToolRepresentation;

import java.util.Optional;

public class AstahModelingTool implements ModelingToolRepresentation {

    @Override
    public ModelPackage rootPackage() {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            return new AstahModelPackage(projectAccessor.getProject());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<ModelPackage> selectedPackage() {

        try {
            IViewManager viewManager = AstahAPI.getAstahAPI().getViewManager();
            IProjectViewManager projectViewManager = viewManager.getProjectViewManager();

            for (IEntity entity : projectViewManager.getSelectedEntities()) {
                if (entity instanceof IPackage) {
                    return Optional.of(new AstahModelPackage((IPackage) entity));
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void selectModelElement(ModelElement modelElement) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void selectModelElementOnCurrentDiagram(ModelElement modelElement) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
