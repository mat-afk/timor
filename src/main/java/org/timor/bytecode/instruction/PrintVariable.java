package org.timor.bytecode.instruction;

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

    }
}
