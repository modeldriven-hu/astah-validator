package hu.modeldriven.astah.validator.tool;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;
import hu.modeldriven.astah.validator.core.ModelElement;
import hu.modeldriven.astah.validator.core.ModelPackage;

import java.util.ArrayList;
import java.util.List;

public class AstahModelPackage implements ModelPackage {

    private final IPackage pkg;

    public AstahModelPackage(IPackage pkg) {
        this.pkg = pkg;
    }

    @Override
    public Object value() {
        return this.pkg;
    }

    @Override
    public String id() {
        return pkg.getId();
    }

    @Override
    public String name() {
        return pkg.getName();
    }

    @Override
    public List<ModelElement> children() {

        List<ModelElement> children = new ArrayList<>();

        for (INamedElement child : pkg.getOwnedElements()) {
            if (child instanceof IPackage){
                children.add(new AstahModelPackage((IPackage) child));
            } else {
                children.add(new AstahModelElement(child));
            }
        }

        return children;
    }
}
