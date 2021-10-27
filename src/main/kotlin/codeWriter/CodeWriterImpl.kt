package codeWriter

import parser.Command
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
     * @param command the command to be written as assembly code
     * @param segment the prefix to determine scoping
     * @param index   which index in the stack arithmetic to emulate
     */
    override fun writePushPop(command: Command, segment: String, index: Int) {
        TODO("Not yet implemented")
    }

    /**
     * Closes the output file / stream.
     */
    override fun close() {
        TODO("Not yet implemented")
    }
}