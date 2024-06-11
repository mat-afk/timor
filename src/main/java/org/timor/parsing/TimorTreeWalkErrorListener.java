package org.timor.parsing;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class TimorTreeWalkErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String message,
            RecognitionException exception
    ) {
        final String errorFormat = "Syntax error at line %d, column %d. More details:\n%s";
        final String errorMessage = String.format(errorFormat, line, charPositionInLine, message);
        System.out.println(errorMessage);
    }
}
