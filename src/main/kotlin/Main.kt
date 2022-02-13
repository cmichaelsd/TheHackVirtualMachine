import virtualMachineTranslator.VirtualMachineTranslator
import java.io.File

const val FILE_ARGUMENT_INDEX = 0
const val VALID_FILE_WRITE_TYPE = "asm"

fun main(args: Array<String>) {
    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    // If no arguments nothing to compile, return.
    if (args.isEmpty()) return

    // Get file from argument.
    val inputFile = File(args[FILE_ARGUMENT_INDEX])

    // The file will be saved as a .asm file in the same directory as the parsed virtual machine file.
    val resultFilePath = "${inputFile.parentFile}/${inputFile.nameWithoutExtension}.$VALID_FILE_WRITE_TYPE"

    // Initialize file.
    val outputFile = File(resultFilePath)

    // Call driver function with parser and code writer instance.
    VirtualMachineTranslator.initialize(inputFile, outputFile)
}