package virtualMachineTranslator

import codeWriter.CodeWriter
import codeWriter.CodeWriterImpl
import command.CommandType
import parser.ParserImpl
import java.io.File
import java.io.IOException

object VirtualMachineTranslator {
    private const val VALID_FILE_WRITE_TYPE = "asm"
    private const val VALID_FILE_READ_TYPE  = "vm"

    lateinit var codeWriter: CodeWriter
    lateinit var outputFile: File

    @Throws(IOException::class)
    fun initialize(inputFile: File) {
        if (!inputFile.exists()) return

        val resultFilePath = if (inputFile.isDirectory) "${inputFile.path}${File.separator}${inputFile.name}.$VALID_FILE_WRITE_TYPE"
        else "${inputFile.parentFile}${File.separator}${inputFile.nameWithoutExtension}.$VALID_FILE_WRITE_TYPE"

        outputFile = File(resultFilePath)

        // Initialize CodeWriterImpl.
        codeWriter = CodeWriterImpl(outputFile)

        try {
            // Determine if the path is a directory or a file
            if (inputFile.isDirectory) inputFile.walk().forEach { writeFile(it) }
            else writeFile(inputFile)
        } catch (e: IOException) {
            throw e
        } finally {
            codeWriter.close()
        }
    }

    private fun writeFile(file: File) {
        // If not valid file type, return.
        if (file.extension != VALID_FILE_READ_TYPE) return

        codeWriter.setFileName(file.nameWithoutExtension)

        val parserImpl = ParserImpl(file)
        while (parserImpl.hasMoreLines()) {
            parserImpl.advance()
            codeWriter.writeComment(parserImpl.currentInstruction.joinToString(" "))
            when (val commandType = parserImpl.commandType()) {
                CommandType.C_PUSH,
                CommandType.C_POP        -> codeWriter.writePushPop(commandType, parserImpl.arg1(), parserImpl.arg2())
                CommandType.C_ARITHMETIC -> codeWriter.writeArithmetic(parserImpl.arg1())
                CommandType.C_LABEL      -> codeWriter.writeLabel(parserImpl.arg1())
                CommandType.C_GOTO       -> codeWriter.writeGoTo(parserImpl.arg1())
                CommandType.C_IF         -> codeWriter.writeIf(parserImpl.arg1())
                CommandType.C_FUNCTION   -> codeWriter.writeFunction(parserImpl.arg1(), parserImpl.arg2())
                CommandType.C_CALL       -> codeWriter.writeCall(parserImpl.arg1(), parserImpl.arg2())
                CommandType.C_RETURN     -> codeWriter.writeReturn()
            }
        }
    }
}