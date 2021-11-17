package codeWriter

import codeWriter.factory.arithmeticLogical.BinomialFactoryImpl
import codeWriter.factory.pushPop.PopFactoryImpl
import codeWriter.factory.pushPop.PushFactoryImpl
import command.CommandType
import java.io.File

class CodeWriterImpl(private val outputFile: File) : CodeWriter {
    /**
     * The name of the output file to be uses in assembly language generation.
     */
    private val fileName = outputFile.nameWithoutExtension

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param command the command to be written as assembly code
     */
    override fun writeArithmetic(command: String) {
        when(command) {
            "add" -> writeToOutputFile(BinomialFactoryImpl().create("D=D+M"))
            "sub" -> writeToOutputFile(BinomialFactoryImpl().create("D=D-M"))
            "and" -> writeToOutputFile(BinomialFactoryImpl().create("D=D&M"))
            "or"  -> writeToOutputFile(BinomialFactoryImpl().create("D=D|M"))
        }
    }

    /**
     * Writes to the output file the assembly code that implements the given arithmetic-logical command.
     *
     * @param commandType the command to be written as assembly code
     * @param segment the prefix to determine scoping
     * @param index   which index in the stack arithmetic to emulate
     */
    override fun writePushPop(commandType: CommandType, segment: String, index: Int) {
        if (commandType == CommandType.C_PUSH) writeToOutputFile(PushFactoryImpl(fileName).create(segment, index))
        if (commandType == CommandType.C_POP) writeToOutputFile(PopFactoryImpl(fileName).create(segment, index))
    }

    /**
     * Closes the output file / stream.
     */
    override fun close() {
        TODO("Not yet implemented")
    }

    private fun writeToOutputFile(list: List<String>) {
        outputFile.bufferedWriter().use { writer ->
            list.forEach { line ->
                writer.write(line)
                writer.newLine()
            }
        }
    }
}