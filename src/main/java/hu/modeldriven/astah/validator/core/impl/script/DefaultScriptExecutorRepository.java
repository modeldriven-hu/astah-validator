package hu.modeldriven.astah.validator.core.impl.script;

public class DefaultScriptExecutorRepository implements ScriptExecutorRepository {
    @Override
    public ScriptExecutor getScriptExecutor(String language) {
        return new GroovyScriptExecutor();
    }

}