package codeWriter

import codeWriter.subroutines.arithmeticLogical.BinomialImpl
import codeWriter.subroutines.stack.PopImpl
import codeWriter.subroutines.stack.PushImpl
import command.CommandType
import java.io.File

class CodeWriterImpl(outputFile: File) : CodeWriter {
    /**
     * The name of the output file to be uses in assembly language generation.
     */
    private val fileName = outputFile.nameWithoutExtension

    private val bufferedWriter = outputFile.bufferedWriter()

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param command the command to be written as assembly code
     */
    override fun writeArithmetic(command: String) {
        when(command) {
            "add" -> writeToOutputFile(BinomialImpl().create("M=D+M"))
            "sub" -> writeToOutputFile(BinomialImpl().create("M=D-M"))
            "and" -> writeToOutputFile(BinomialImpl().create("M=D&M"))
            "or"  -> writeToOutputFile(BinomialImpl().create("M=D|M"))
        }
    }

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param commandType the command to be written as assembly code
     * @param segment     the prefix to determine scoping
     * @param index       which index in the stack arithmetic to emulate
     */
    override fun writePushPop(commandType: CommandType, segment: String, index: Int) {
        if (commandType == CommandType.C_PUSH) writeToOutputFile(PushImpl(fileName).create(segment, index))
        if (commandType == CommandType.C_POP) writeToOutputFile(PopImpl(fileName).create(segment, index))
    }

    /**
     * Closes the output file / stream.
     */
    override fun close() {
        bufferedWriter.close()
    }

    private fun writeToOutputFile(list: List<String>) {
        list.forEach { bufferedWriter.appendLine(it) }
    }
}