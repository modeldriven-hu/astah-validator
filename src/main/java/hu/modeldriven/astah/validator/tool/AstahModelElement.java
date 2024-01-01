package hu.modeldriven.astah.validator.tool;

import com.change_vision.jude.api.inf.model.INamedElement;
import hu.modeldriven.validator.core.ModelElement;

public class AstahModelElement implements ModelElement {

    private final INamedElement element;

    public AstahModelElement(INamedElement element){
        this.element = element;
    }

    @Override
    public Object value() {
        return element;
    }

    @Override
    public String id() {
        return element.getId();
    }

    @Override
    public String name() {
        return element.getName();
    }
}
