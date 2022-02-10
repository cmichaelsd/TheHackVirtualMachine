package codeWriter.subroutines.arithmeticLogical

import java.util.concurrent.atomic.AtomicInteger

class Comparison(private val sequence: AtomicInteger) : Operation {
    override fun create(operation: String): List<String> {
        sequence.set(sequence.get() + 1)

        val result = mutableListOf<String>()
        result.addAll(addressMemoryDecrement())
        result.add("D=M")
        result.addAll(addressMemoryDecrement())
        result.add("D=M-D")
        result.addAll(listOf(
            "@COMP.${sequence.get()}.TRUE",
            operation,
            "@COMP.${sequence.get()}.FALSE",
            "0;JMP",
            "(COMP.${sequence}.TRUE)",
        ))
        result.addAll(stackPointerAddressSetToMemory())
        result.add("M=-1")
        result.addAll(incrementStackPointer())
        result.addAll(listOf(
            "@COMP.${sequence}.END",
            "0;JMP",
            "(COMP.${sequence}.FALSE)"
        ))
        result.addAll(stackPointerAddressSetToMemory())
        result.add("M=0")
        result.addAll(incrementStackPointer())
        result.add("(COMP.${sequence}.END)")
        return result
    }

    private fun addressMemoryDecrement(): List<String> {
        return listOf("@SP", "AM=M-1")
    }

    private fun stackPointerAddressSetToMemory(): List<String> {
        return listOf("@SP", "A=M")
    }

    private fun incrementStackPointer(): List<String> {
        return listOf("@SP", "M=M+1")
    }
}