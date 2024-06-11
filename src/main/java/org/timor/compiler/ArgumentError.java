package org.timor.compiler;

public enum ArgumentError {
    NONE (""),
    NO_FILE ("Specify files for compilation."),
    BAD_FILE_EXTENSION ("File has to have a .tmr extension");

    private final String message;

    ArgumentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
