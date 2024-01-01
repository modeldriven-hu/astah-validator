package hu.modeldriven.astah.validator.core.impl.script;

public interface ScriptExecutor {

    Object execute(String script, Object argument) throws ScriptExecutionException;

}
