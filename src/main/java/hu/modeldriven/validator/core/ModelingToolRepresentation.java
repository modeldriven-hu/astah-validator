package hu.modeldriven.validator.core;

import java.util.Optional;

public interface ModelingToolRepresentation {

    ModelPackage rootPackage();

    Optional<ModelPackage> selectedPackage();

    void selectModelElement(ModelElement modelElement);

    void selectModelElementOnCurrentDiagram(ModelElement modelElement);

}
