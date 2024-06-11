package org.timor.compiler;

import org.apache.commons.lang3.StringUtils;
import org.timor.bytecode.BytecodeGenerator;
import org.timor.bytecode.instruction.Instruction;
import org.timor.parsing.SyntaxTreeTraverser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Queue;

public class Compiler {

    private static final String OUTPUT_DIR = "examples/";

    public void compile(String[] args) {
        final ArgumentError error = validateArguments(args);

        if (error != ArgumentError.NONE) {
            System.out.println(error.getMessage());
            return;
        }

        final File timorFile = new File(args[0]);
        String fileName = timorFile.getName();
        String fileAbsolutePath = timorFile.getAbsolutePath();
        String className = StringUtils.remove(fileName, ".tmr");

        final Queue<Instruction> instructions = new SyntaxTreeTraverser().getInstructions(fileAbsolutePath);
        final byte[] bytecode = new BytecodeGenerator().generateBytecode(instructions, className);

        saveBytecodeToClassFile(fileName, bytecode);
    }

    private ArgumentError validateArguments(String[] args) {
        if (args.length != 1) {
            return ArgumentError.NO_FILE;
        }

        String filePath = args[0];
        if (!filePath.endsWith(".tmr")) {
            return ArgumentError.BAD_FILE_EXTENSION;
        }

        return ArgumentError.NONE;
    }

    private static void saveBytecodeToClassFile(String fileName, byte[] bytecode) {
        final String classFile = StringUtils.replace(fileName, ".tmr", ".class");
        final String classFilePath = OUTPUT_DIR + classFile;

        OutputStream os;
        try {
            os = new FileOutputStream(classFilePath);
            os.write(bytecode);

            os.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
