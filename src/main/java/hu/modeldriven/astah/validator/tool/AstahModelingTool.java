package hu.modeldriven.astah.validator.tool;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IEntity;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.presentation.IPresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.view.IDiagramViewManager;
import com.change_vision.jude.api.inf.view.IProjectViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;
import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ModelPackage;
import hu.modeldriven.astah.validator.core.ModelingToolException;
import hu.modeldriven.astah.validator.core.ModelingToolRepresentation;

import java.util.Optional;

public class AstahModelingTool implements ModelingToolRepresentation {

    @Override
    public ModelPackage rootPackage() throws ModelingToolException {
        try {
            ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
            return new AstahModelPackage(projectAccessor.getProject());
        } catch (Exception e) {
            throw new ModelingToolException(e);
        }
    }

    @Override
    public Optional<ModelPackage> selectedPackage() throws ModelingToolException {

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
            throw new ModelingToolException(e);
        }
    }

    @Override
    public void selectModelElement(ModelElement modelElement) throws ModelingToolException {
        try {
            AstahAPI api = AstahAPI.getAstahAPI();
            IViewManager manager = api.getViewManager();
            IProjectViewManager projectViewManager = manager.getProjectViewManager();
            projectViewManager.showInStructureTree((INamedElement) modelElement.value());
        } catch (Exception e) {
            throw new ModelingToolException(e);
        }
    }

    @Override
    public void selectModelElementOnCurrentDiagram(ModelElement modelElement) throws ModelingToolException {
        try {
            AstahAPI api = AstahAPI.getAstahAPI();

            IDiagramViewManager diagramViewManager = api.getViewManager().getDiagramViewManager();

            IDiagram currentDiagram = diagramViewManager.getCurrentDiagram();

            if (currentDiagram != null) {
                for (IPresentation presentation : currentDiagram.getPresentations()) {
                    if (modelElement.value() != null && modelElement.value().equals(presentation.getModel())) {
                        diagramViewManager.select(presentation);
                    }
                }
            }

        } catch (Exception e) {
            throw new ModelingToolException(e);
        }
    }
}
