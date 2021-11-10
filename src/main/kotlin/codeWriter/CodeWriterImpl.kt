package codeWriter

import codeWriter.writer.WritePopImpl
import codeWriter.writer.WritePushImpl
import command.CommandType
import java.io.File

class CodeWriterImpl(file: File) : CodeWriter {

    // Open an output file
    // Write into that file
    // Get file name here

    val fileName = ""

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param command the command to be written as assembly code
     */
    override fun writeArithmetic(command: String) {
        TODO("Not yet implemented")
    }

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param commandType the command to be written as assembly code
     * @param segment the prefix to determine scoping
     * @param index   which index in the stack arithmetic to emulate
     */
    override fun writePushPop(commandType: CommandType, segment: String, index: Int) {
        if (commandType == CommandType.C_PUSH) WritePushImpl(fileName).write(segment, index)
        if (commandType == CommandType.C_POP) WritePopImpl(fileName).write(segment, index)
    }

    /**
     * Closes the output file / stream.
     */
    override fun close() {
        TODO("Not yet implemented")
    }
}