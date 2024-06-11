package org.timor.parsing;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.timor.TimorLexer;
import org.timor.TimorParser;
import org.timor.bytecode.instruction.Instruction;

import java.io.IOException;
import java.util.Queue;

public class SyntaxTreeTraverser {

    public Queue<Instruction> getInstructions(String fileAbsolutePath) {
        CharStream charStream;

        try {
            charStream = new ANTLRFileStream(fileAbsolutePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TimorLexer lexer = new TimorLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        TimorParser parser = new TimorParser(tokenStream);
        TimorTreeWalkListener listener = new TimorTreeWalkListener();
        TimorTreeWalkErrorListener errorListener = new TimorTreeWalkErrorListener();

        parser.addErrorListener(errorListener);
        parser.addParseListener(listener);
        parser.compilationUnit();

        return listener.getInstructionsQueue();
    }
}
