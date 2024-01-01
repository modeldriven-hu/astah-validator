package hu.modeldriven.validator.core.impl.script;

public interface ScriptExecutor {

    Object execute(String script, Object argument) throws ScriptExecutionException;

}
