package org.timor.bytecode.instruction;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.timor.TimorLexer;
import org.timor.domain.Variable;

public class VariableDeclaration implements Instruction, Opcodes {

    private final Variable variable;

    public VariableDeclaration(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        final int type = variable.getType();

        switch (type) {
            case TimorLexer.NUMBER -> {
                int value = Integer.parseInt(variable.getValue());
                methodVisitor.visitIntInsn(BIPUSH, value);
                methodVisitor.visitVarInsn(ISTORE, variable.getIdentifier());
            }
            case TimorLexer.STRING -> {
                methodVisitor.visitLdcInsn(variable.getValue());
                methodVisitor.visitVarInsn(ASTORE, variable.getIdentifier());
            }
        }
    }
}
