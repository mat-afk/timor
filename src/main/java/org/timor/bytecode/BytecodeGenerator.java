package org.timor.bytecode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.timor.bytecode.instruction.Instruction;
import org.timor.bytecode.instruction.VariableDeclaration;

import java.util.Queue;

public class BytecodeGenerator implements Opcodes {

    public byte[] generateBytecode(Queue<Instruction> instructions, String name) {
        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;

        classWriter.visit(
                52,
                ACC_PUBLIC + ACC_SUPER,
                name,
                null,
                "java/lang/Object",
                null
        );
        {
            methodVisitor = classWriter.visitMethod(
                    ACC_PUBLIC + ACC_STATIC,
                    "main",
                    "([Ljava/lang/String;)V",
                    null,
                    null
            );

            final long localVariablesCount = instructions.stream()
                    .filter(instruction -> instruction instanceof VariableDeclaration)
                    .count();

            final int maxStack = 100;

            for (Instruction instruction : instructions) {
                instruction.apply(methodVisitor);
            }
            methodVisitor.visitInsn(RETURN);

            methodVisitor.visitMaxs(maxStack, (int) localVariablesCount);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
