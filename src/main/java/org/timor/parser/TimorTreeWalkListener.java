package org.timor.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.timor.TimorBaseListener;
import org.timor.TimorParser;
import org.timor.bytecode.instruction.PrintVariable;
import org.timor.bytecode.instruction.VariableDeclaration;
import org.timor.domain.Variable;
import org.timor.bytecode.instruction.Instruction;

import java.util.*;

public class TimorTreeWalkListener extends TimorBaseListener {

    Queue<Instruction> instructionsQueue = new ArrayDeque<>();
    Map<String, Variable> variables = new HashMap<>();

    public Queue<Instruction> getInstructionsQueue() {
        return instructionsQueue;
    }

    @Override
    public void exitVariable(@NotNull TimorParser.VariableContext context) {
        final TerminalNode varName = context.IDENTIFIER();
        final TimorParser.ValueContext varValue = context.value();

        final int varIndex = variables.size();
        final int varType = varValue.getStart().getType();
        final String varTextValue = varValue.getText();
        Variable var = new Variable(varIndex, varType, varTextValue);

        variables.put(varName.getText(), var);
        instructionsQueue.add(new VariableDeclaration(var));

        logVariableDeclarationStatementFound(varName, varValue);
    }

    @Override
    public void exitPrint(@NotNull TimorParser.PrintContext context) {
        final TerminalNode varName = context.IDENTIFIER();
        final boolean printedNotDeclaredVariable = !variables.containsKey(varName.getText());

        if (printedNotDeclaredVariable) {
            logPrintNotDeclaredVariable(varName.getText());
            return;
        }

        final Variable variable = variables.get(varName.getText());
        instructionsQueue.add(new PrintVariable(variable));

        logPrintStatementFound(varName, variable);
    }

    private void logVariableDeclarationStatementFound(TerminalNode varName, TimorParser.ValueContext varValue) {
        final int line = varName.getSymbol().getLine();
        final String format = "LOG: You declared variable named '%s' with value of '%s' at line '%s'. \n";
        System.out.printf(format, varName, varValue.getText(), line);
    }

    private void logPrintStatementFound(TerminalNode varName, Variable variable) {
        final int line = varName.getSymbol().getLine();
        final String format = "LOG: You instructed to print variable '%s' which has value of '%s' at line '%s'. \n";
        System.out.printf(format, variable.getIdentifier(), variable.getValue(), line);
    }

    private void logPrintNotDeclaredVariable(String variableName) {
        final String format = "ERROR: you are trying to print variable '%s' which has not been declared. \n";
        System.out.printf(format, variableName);
    }
}
