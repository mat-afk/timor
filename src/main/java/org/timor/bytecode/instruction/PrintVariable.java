package org.timor.bytecode.instruction;

import org.timor.TimorLexer;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.timor.domain.Variable;

public class PrintVariable implements Instruction, Opcodes {

    private final Variable variable;

    public PrintVariable(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void apply(MethodVisitor methodVisitor) {
        final int type = variable.getType();
        final int identifier = variable.getIdentifier();
        methodVisitor.visitFieldInsn(
                GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;"
        );

        switch (type) {
            case TimorLexer.NUMBER -> {
                methodVisitor.visitVarInsn(ILOAD, identifier);
                methodVisitor.visitMethodInsn(
                        INVOKEVIRTUAL,
                        "java/io/PrintStream",
                        "println",
                        "(I)V",
                        false
                );
            }
            case TimorLexer.STRING -> {
                methodVisitor.visitVarInsn(ALOAD, identifier);
                methodVisitor.visitMethodInsn(
                        INVOKEVIRTUAL,
                        "java/io/PrintStream",
                        "println",
                        "(Ljava/lang/String;)V",
                        false
                );
            }
        }
    }
}
