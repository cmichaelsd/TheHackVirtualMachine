package codeWriter.subroutines.arithmeticLogical

import java.util.concurrent.atomic.AtomicInteger

class Comparison(private val sequence: AtomicInteger) : Operation {
    override fun create(operation: String): String {
        sequence.set(sequence.get() + 1)

        val result = StringBuilder()
        addressMemoryDecrement(result)
        result.appendLine("D=M")
        addressMemoryDecrement(result)
        result.appendLine("D=M-D")
        result.appendLine("@COMP.${sequence.get()}.TRUE")
        result.appendLine(operation)
        result.appendLine("@COMP.${sequence.get()}.FALSE")
        result.appendLine("0;JMP")
        result.appendLine("(COMP.${sequence}.TRUE)")
        stackPointerAddressSetToMemory(result)
        result.appendLine("M=-1")
        incrementStackPointer(result)
        result.appendLine("@COMP.${sequence}.END")
        result.appendLine("0;JMP")
        result.appendLine("(COMP.${sequence}.FALSE)")
        stackPointerAddressSetToMemory(result)
        result.appendLine("M=0")
        incrementStackPointer(result)
        result.appendLine("(COMP.${sequence}.END)")
        return result.toString()
    }

    private fun addressMemoryDecrement(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("AM=M-1")
    }

    private fun stackPointerAddressSetToMemory(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("A=M")
    }

    private fun incrementStackPointer(stringBuilder: StringBuilder) {
        stringBuilder.appendLine("@SP")
        stringBuilder.appendLine("M=M+1")
    }
}