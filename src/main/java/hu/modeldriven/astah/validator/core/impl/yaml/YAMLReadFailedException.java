package hu.modeldriven.astah.validator.core.impl.yaml;

public class YAMLReadFailedException extends RuntimeException {

    public YAMLReadFailedException(Exception e) {
        super(e);
    }

}
