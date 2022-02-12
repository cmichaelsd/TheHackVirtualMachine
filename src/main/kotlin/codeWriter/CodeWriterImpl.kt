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
    private var fileName: String? = null // outputFile.nameWithoutExtension

    private val bufferedWriter = outputFile.bufferedWriter()

    private var sequence = AtomicInteger(-1)

    init {
        writeToOutputFile(listOf("@256", "D=A", "@SP", "M=D"))
    }

    /**
     * Informs that the translation of a new VM file has started (called by the VMTranslator).
     *
     * @param fileName the name for the compiled file
     */
    override fun setFileName(fileName: String) {
        TODO("Not yet implemented")
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
     * Writes assembly code that effects the label command.
     *
     * @param label the label for the label command
     */
    override fun writeLabel(label: String) {
        TODO("Not yet implemented")
    }

    /**
     * Writes assembly code that effects the goto command.
     *
     * @param label the label for the goto command
     */
    override fun writeGoTo(label: String) {
        TODO("Not yet implemented")
    }

    /**
     * Writes assembly code that effects the if-goto command.
     *
     * @param label the label for the if-goto command
     */
    override fun writeIf(label: String) {
        TODO("Not yet implemented")
    }

    /**
     * Writes assembly code that effects the function command.
     *
     * @param functionName the name for the function to write
     * @param nVars        the number of variables for the function
     */
    override fun writeFunction(functionName: String, nVars: Int) {
        TODO("Not yet implemented")
    }

    /**
     * Writes assembly code that effects the call command.
     *
     * @param functionName the name for the function to call
     * @param nArgs        the number of arguments for the function
     */
    override fun writeCall(functionName: String, nArgs: Int) {
        TODO("Not yet implemented")
    }

    /**
     * Writes assembly code that effects the return command.
     */
    override fun writeReturn() {
        TODO("Not yet implemented")
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