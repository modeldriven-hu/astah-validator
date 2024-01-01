package hu.modeldriven.astah.validator.core;

import java.util.Optional;

public interface ModelingToolRepresentation {

    ModelPackage rootPackage() throws ModelingToolException;

    Optional<ModelPackage> selectedPackage() throws ModelingToolException;

    void selectModelElement(ModelElement modelElement) throws ModelingToolException;

    void selectModelElementOnCurrentDiagram(ModelElement modelElement) throws ModelingToolException;

}
