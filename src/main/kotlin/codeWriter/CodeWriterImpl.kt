package codeWriter

import codeWriter.subroutines.arithmeticLogical.Binomial
import codeWriter.subroutines.arithmeticLogical.Comparison
import codeWriter.subroutines.arithmeticLogical.Singular
import codeWriter.subroutines.stack.PopImpl
import codeWriter.subroutines.stack.PushImpl
import command.CommandType
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

class CodeWriterImpl(outputFile: File) : CodeWriter {
    /**
     * The name of the output file to be uses in assembly language generation.
     */
    private val fileName = outputFile.nameWithoutExtension

    private val bufferedWriter = outputFile.bufferedWriter()

    private var sequence = AtomicInteger(-1)

    init {
        writeToOutputFile(listOf("@256", "D=A", "@SP", "M=D"))
    }

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param command the command to be written as assembly code
     */
    override fun writeArithmetic(command: String) {
        when(command) {
            "add" -> writeToOutputFile(Binomial.create("M=D+M"))
            "sub" -> writeToOutputFile(Binomial.create("M=M-D"))
            "and" -> writeToOutputFile(Binomial.create("M=D&M"))
            "or"  -> writeToOutputFile(Binomial.create("M=D|M"))

            "neg" -> writeToOutputFile(Singular.create("M=-M"))
            "not" -> writeToOutputFile(Singular.create("M=!M"))

            "eq"  -> writeToOutputFile(Comparison(sequence).create("D;JEQ"))
            "lt"  -> writeToOutputFile(Comparison(sequence).create("D;JLT"))
            "gt"  -> writeToOutputFile(Comparison(sequence).create("D;JGT"))
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