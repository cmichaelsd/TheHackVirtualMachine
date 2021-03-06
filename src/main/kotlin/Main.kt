import virtualMachineTranslator.VirtualMachineTranslator
import java.io.File

const val FILE_ARGUMENT_INDEX = 0

fun main(args: Array<String>) {
    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    // If no arguments nothing to compile, return.
    if (args.isEmpty()) return

    // Get file from argument.
    val inputFile = File(args[FILE_ARGUMENT_INDEX])

    // Call driver function with parser and code writer instance.
    VirtualMachineTranslator.initialize(inputFile)
}