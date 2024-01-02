package hu.modeldriven.astah.validator.core.impl.script;

import org.codehaus.groovy.control.MultipleCompilationErrorsException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroovyScriptExecutionException extends ScriptExecutionException {

    private static final String TEMP_FILENAME = "Script1.groovy";

    public GroovyScriptExecutionException(Exception e) {
        super(e, calculateLineNumberFromException(e));
    }

    private static int calculateLineNumberFromException(Exception ex) {
        if (ex instanceof MultipleCompilationErrorsException) {
            return extractLineNumberFromCompilationError(ex.getMessage());
        } else {
            return extractLineNumberFromStackTrace(ex.getStackTrace());
        }
    }

    private static int extractLineNumberFromCompilationError(String errorMessage) {
        Pattern pattern = Pattern.compile("@ line (\\d+)");
        Matcher matcher = pattern.matcher(errorMessage);

        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                return MISSING_LINE;
            }
        }

        return MISSING_LINE;
    }

    private static int extractLineNumberFromStackTrace(StackTraceElement[] stackTrace) {
        return Arrays.stream(stackTrace)
                .filter(e -> TEMP_FILENAME.equals(e.getFileName())) // matching filename
                .map(StackTraceElement::getLineNumber) // get line number
                .findFirst()                           // find the first one
                .orElse(MISSING_LINE);                 // or else set -1 meaning we have no idea
    }

}