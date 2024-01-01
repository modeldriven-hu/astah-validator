package hu.modeldriven.validator.core.impl.script;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyScriptExecutor implements ScriptExecutor {

    @Override
    public Object execute(String script, Object argument) throws ScriptExecutionException {
        Binding binding = new Binding();
        binding.setVariable("arg1", argument);
        GroovyShell shell = new GroovyShell(binding);
        try {
            return shell.evaluate(script);
        } catch (Exception ex) {
            throw new GroovyScriptExecutionException(ex);
        }
    }
}
