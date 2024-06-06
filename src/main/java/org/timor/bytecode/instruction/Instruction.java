package org.timor.bytecode.instruction;

import org.objectweb.asm.MethodVisitor;

public interface Instruction {

    void apply(MethodVisitor methodVisitor);
}
