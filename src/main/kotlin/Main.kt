import codeWriter.CodeWriterImpl
import parser.ParserImpl
import virtualMachineTranslator.VirtualMachineTranslator
import java.io.File

const val FILE_ARGUMENT_INDEX = 0
const val VALID_FILE_READ_TYPE = "vm"
const val VALID_FILE_WRITE_TYPE = "asm"

fun main(args: Array<String>) {
    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    // If no arguments nothing to compile, return.
    if (args.isEmpty()) return

    // Get file from argument.
    val file = File(args[FILE_ARGUMENT_INDEX])

    // If not valid file type, return.
    if (!file.exists() || file.extension != VALID_FILE_READ_TYPE) return

    // The file will be saved as a .asm file in the same directory as the parsed virtual machine file.
    val resultFilePath = "${file.parentFile}/${file.nameWithoutExtension}.$VALID_FILE_WRITE_TYPE"

    // Initialize file.
    val outputFile = File(resultFilePath)

    // Initialize ParserImpl.
    val parserImpl = ParserImpl(file)

    // Initialize CodeWriterImpl.
    val codeWriter = CodeWriterImpl(outputFile)

    // Call driver function with parser and code writer instance.
    VirtualMachineTranslator.initialize(parserImpl, codeWriter)
}