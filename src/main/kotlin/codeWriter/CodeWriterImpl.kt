package codeWriter

import command.CommandType
import java.io.File

class CodeWriterImpl(file: File) : CodeWriter {
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
        if (commandType == CommandType.C_PUSH) {

        }
    }

    /**
     * Closes the output file / stream.
     */
    override fun close() {
        TODO("Not yet implemented")
    }
}